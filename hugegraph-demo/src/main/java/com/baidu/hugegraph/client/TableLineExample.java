package com.baidu.hugegraph.client;

import com.baidu.hugegraph.driver.GraphManager;
import com.baidu.hugegraph.driver.HugeClient;
import com.baidu.hugegraph.driver.SchemaManager;
import com.baidu.hugegraph.structure.constant.T;
import com.baidu.hugegraph.structure.graph.Vertex;

/**
 * 实现一个数据血缘的demo
 *
 * @author lizu
 * @since 2022/8/21
 */
public class TableLineExample {

    public static final String DATABASE = "database";

    public static final String TABLE = "table";

    public static final String FILED_NAME = "filedName";

    private HugeClient getHugeClient() {
        HugeClient hugeClient = null;
        try {
            hugeClient = HugeClient.builder("http://localhost:8080", "hugegraph")
                // 默认 20s 超时
                .configTimeout(20)
                // 默认未开启用户权限
                .configUser("**", "**")
                .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hugeClient;
    }

    /**
     * 定义schema:属性,顶点,边
     */
    private void initManagerSchema(HugeClient hugeClient) {
        SchemaManager schema = hugeClient.schema();
        schema.propertyKey("databaseName").asText().ifNotExist().create();
        schema.propertyKey("tableName").asText().ifNotExist().create();
        schema.propertyKey("filedName").asText().ifNotExist().create();
        schema.propertyKey("style").asText().ifNotExist().create();

        //VertexLabel 用来定义顶点类型，描述顶点的约束信息
        schema.vertexLabel("database")
            .properties("databaseName", "style")
            .primaryKeys("databaseName")
            .ifNotExist()
            .create();

        schema.vertexLabel("table")
            .properties("tableName", "databaseName", "style")
            .primaryKeys("tableName")
            .ifNotExist()
            .create();

        schema.vertexLabel("filedName")
            .properties("filedName", "tableName", "databaseName", "style")
            .primaryKeys("filedName", "tableName")
            .ifNotExist()
            .create();

        //EdgeLabel 用来定义边类型，描述边的约束信息。
        schema.edgeLabel("table_from_database")
            .sourceLabel("table")
            .targetLabel("database")
            .ifNotExist()
            .create();

        schema.edgeLabel("field_from_table")
            .sourceLabel("filedName")
            .targetLabel("table")
            .ifNotExist()
            .create();

    }


    /**
     * 创建图,顶点+边
     */
    private Vertex addDatabaseNameVertex(HugeClient hugeClient, String vertexLabel, String name,
        String color) {
        GraphManager graph = hugeClient.graph();
        return graph.addVertex(T.label, vertexLabel, "databaseName", name, "style", color);
    }

    private Vertex addTableNameVertex(HugeClient hugeClient, String vertexLabel, String name,
        String databaseName,
        String color) {
        GraphManager graph = hugeClient.graph();
        return graph
            .addVertex(T.label, vertexLabel, "tableName", name, "databaseName", databaseName,
                "style", color);
    }

    private Vertex addFieldNameVertex(HugeClient hugeClient, String vertexLabel, String name,
        String tableName, String databaseName,
        String color) {
        GraphManager graph = hugeClient.graph();
        return graph
            .addVertex(T.label, vertexLabel, "filedName", name, "tableName", tableName,
                "databaseName",
                databaseName, "style", color);
    }

    /**
     * 创建图,顶点+边
     */
    private void addEdge(Vertex vertex, Vertex destVertex,
        String edgeLabel) {
        vertex.addEdge(edgeLabel, destVertex);
    }


    /**
     * 库和表 表和表 表和字段
     */
    public static void main(String[] args) {

        //库DATA_02,一个表:ods_tag  字段:id,tag,value
        TableLineExample example = new TableLineExample();
        HugeClient hugeClient = null;
        try {
            hugeClient = example.getHugeClient();
            example.initManagerSchema(hugeClient);
            //库 DATA_01,三个表:ods_user,dim_user_profile,dwd_user_event
            //字段 都是ID
            Vertex DATA_01 = example.addDatabaseNameVertex(hugeClient, DATABASE, "DATA_01", "red");

            Vertex ods_user = example
                .addTableNameVertex(hugeClient, TABLE, "ods_user", "DATA_01", "green");
            Vertex dim_user_profile = example
                .addTableNameVertex(hugeClient, TABLE, "dim_user_profile", "DATA_01", "green");
            Vertex dwd_user_event = example
                .addTableNameVertex(hugeClient, TABLE, "dwd_user_event", "DATA_01", "green");

            Vertex ods_user_id = example
                .addFieldNameVertex(hugeClient, FILED_NAME, "id", "ods_user", "DATA_01", "yellow");

            Vertex ods_user_name = example
                .addFieldNameVertex(hugeClient, FILED_NAME, "name", "ods_user", "DATA_01",
                    "yellow");

            Vertex dim_user_profile_id = example
                .addFieldNameVertex(hugeClient, FILED_NAME, "id", "dim_user_profile", "DATA_01",
                    "yellow");

            Vertex dwd_user_event_id = example
                .addFieldNameVertex(hugeClient, FILED_NAME, "id", "dwd_user_event", "DATA_01",
                    "yellow");

            example.addEdge(ods_user, DATA_01, "table_from_database");
            example.addEdge(dim_user_profile, DATA_01, "table_from_database");
            example.addEdge(dwd_user_event, DATA_01, "table_from_database");

            example.addEdge(ods_user_id, ods_user, "field_from_table");
            example.addEdge(ods_user_name, ods_user, "field_from_table");

            example.addEdge(dim_user_profile_id, dim_user_profile, "field_from_table");
            example.addEdge(dwd_user_event_id, dwd_user_event, "field_from_table");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hugeClient.close();
        }

    }


}
