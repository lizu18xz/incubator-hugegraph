/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.config;

import static com.baidu.hugegraph.config.OptionChecker.allowValues;
import static com.baidu.hugegraph.config.OptionChecker.disallowEmpty;
import static com.baidu.hugegraph.config.OptionChecker.nonNegativeInt;
import static com.baidu.hugegraph.config.OptionChecker.positiveInt;
import static com.baidu.hugegraph.config.OptionChecker.rangeInt;

public class ServerOptions extends OptionHolder {

    private ServerOptions() {
        super();
    }

    private static volatile ServerOptions instance;

    public static synchronized ServerOptions instance() {
        if (instance == null) {
            instance = new ServerOptions();
            instance.registerOptions();
        }
        return instance;
    }

    public static final ConfigOption<String> REST_SERVER_URL =
            new ConfigOption<>(
                    "restserver.url",
                    "The url for listening of hugeserver.",
                    disallowEmpty(),
                    "http://127.0.0.1:8080"
            );

    public static final ConfigOption<String> SERVER_ID =
            new ConfigOption<>(
                    "server.id",
                    "The id of hugegraph-server.",
                    disallowEmpty(),
                    "server-1"
            );

    public static final ConfigOption<String> SERVER_ROLE =
            new ConfigOption<>(
                    "server.role",
                    "The role of nodes in the cluster, available types are " +
                    "[master, worker, computer]",
                    allowValues("master", "worker", "computer"),
                    "master"
            );

    public static final ConfigOption<Integer> MAX_WORKER_THREADS =
            new ConfigOption<>(
                    "restserver.max_worker_threads",
                    "The maximum worker threads of rest server.",
                    rangeInt(2, Integer.MAX_VALUE),
                    2 * CoreOptions.CPUS
            );

    public static final ConfigOption<Integer> MIN_FREE_MEMORY =
            new ConfigOption<>(
                    "restserver.min_free_memory",
                    "The minimum free memory(MB) of rest server, requests " +
                    "will be rejected when the available memory of system " +
                    "is lower than this value.",
                    nonNegativeInt(),
                    64
            );

    public static final ConfigOption<Integer> REQUEST_TIMEOUT =
            new ConfigOption<>(
                    "restserver.request_timeout",
                    "The time in seconds within which a request must complete, " +
                    "-1 means no timeout.",
                    rangeInt(-1, Integer.MAX_VALUE),
                    30
            );

    public static final ConfigOption<Integer> CONN_IDLE_TIMEOUT =
            new ConfigOption<>(
                    "restserver.connection_idle_timeout",
                    "The time in seconds to keep an inactive connection " +
                    "alive, -1 means no timeout.",
                    rangeInt(-1, Integer.MAX_VALUE),
                    30
            );

    public static final ConfigOption<Integer> CONN_MAX_REQUESTS =
            new ConfigOption<>(
                    "restserver.connection_max_requests",
                    "The max number of HTTP requests allowed to be processed " +
                    "on one keep-alive connection, -1 means unlimited.",
                    rangeInt(-1, Integer.MAX_VALUE),
                    256
            );

    public static final ConfigOption<String> GREMLIN_SERVER_URL =
            new ConfigOption<>(
                    "gremlinserver.url",
                    "The url of gremlin server.",
                    disallowEmpty(),
                    "http://127.0.0.1:8182"
            );

    public static final ConfigOption<Integer> GREMLIN_SERVER_TIMEOUT =
            new ConfigOption<>(
                    "gremlinserver.timeout",
                    "The timeout in seconds of waiting for gremlin server.",
                    positiveInt(),
                    30
            );

    public static final ConfigOption<Integer> GREMLIN_SERVER_MAX_ROUTE =
            new ConfigOption<>(
                    "gremlinserver.max_route",
                    "The max route number for gremlin server.",
                    positiveInt(),
                    2 * CoreOptions.CPUS
            );

    public static final ConfigOption<String> GRAPHS =
            new ConfigOption<>(
                    "graphs",
                    "The directory store graphs' config file.",
                    disallowEmpty(),
                    "./conf/graphs"
            );

    public static final ConfigOption<Integer> MAX_VERTICES_PER_BATCH =
            new ConfigOption<>(
                    "batch.max_vertices_per_batch",
                    "The maximum number of vertices submitted per batch.",
                    positiveInt(),
                    500
            );

    public static final ConfigOption<Integer> MAX_EDGES_PER_BATCH =
            new ConfigOption<>(
                    "batch.max_edges_per_batch",
                    "The maximum number of edges submitted per batch.",
                    positiveInt(),
                    500
            );

    public static final ConfigOption<Integer> MAX_WRITE_RATIO =
            new ConfigOption<>(
                    "batch.max_write_ratio",
                    "The maximum thread ratio for batch writing, " +
                    "only take effect if the batch.max_write_threads is 0.",
                    rangeInt(0, 100),
                    50
            );

    public static final ConfigOption<Integer> MAX_WRITE_THREADS =
            new ConfigOption<>(
                    "batch.max_write_threads",
                    "The maximum threads for batch writing, " +
                    "if the value is 0, the actual value will be set to " +
                    "batch.max_write_ratio * restserver.max_worker_threads.",
                    nonNegativeInt(),
                    0);

    public static final ConfigOption<String> RAFT_GROUP_PEERS =
            new ConfigOption<>(
                    "raft.group_peers",
                    "The rpc address of raft group initial peers.",
                    disallowEmpty(),
                    "127.0.0.1:8090"
            );

    public static final ConfigOption<Boolean> ALLOW_TRACE =
            new ConfigOption<>(
                    "exception.allow_trace",
                    "Whether to allow exception trace stack.",
                    disallowEmpty(),
                    false
            );

    public static final ConfigOption<String> AUTHENTICATOR =
            new ConfigOption<>(
                    "auth.authenticator",
                    "The class path of authenticator implementation. " +
                    "e.g., com.baidu.hugegraph.auth.StandardAuthenticator, " +
                    "or com.baidu.hugegraph.auth.ConfigAuthenticator.",
                    null,
                    ""
            );

    public static final ConfigOption<String> AUTH_GRAPH_STORE =
            new ConfigOption<>(
                    "auth.graph_store",
                    "The name of graph used to store authentication information, " +
                    "like users, only for com.baidu.hugegraph.auth.StandardAuthenticator.",
                    disallowEmpty(),
                    "hugegraph"
            );

    public static final ConfigOption<String> AUTH_ADMIN_TOKEN =
            new ConfigOption<>(
                    "auth.admin_token",
                    "Token for administrator operations, " +
                    "only for com.baidu.hugegraph.auth.ConfigAuthenticator.",
                    disallowEmpty(),
                    "162f7848-0b6d-4faf-b557-3a0797869c55"
            );

    public static final ConfigListOption<String> AUTH_USER_TOKENS =
            new ConfigListOption<>(
                    "auth.user_tokens",
                    "The map of user tokens with name and password, " +
                    "only for com.baidu.hugegraph.auth.ConfigAuthenticator.",
                    disallowEmpty(),
                    "hugegraph:9fd95c9c-711b-415b-b85f-d4df46ba5c31"
            );

    public static final ConfigOption<String> AUTH_REMOTE_URL =
            new ConfigOption<>(
                    "auth.remote_url",
                    "If the address is empty, it provide auth service, " +
                    "otherwise it is auth client and also provide auth service " +
                    "through rpc forwarding. The remote url can be set to " +
                    "multiple addresses, which are concat by ','.",
                    null,
                    ""
            );

    public static final ConfigOption<String> SSL_KEYSTORE_FILE =
            new ConfigOption<>(
                    "ssl.keystore_file",
                    "The path of server keystore file used when https " +
                    "protocol is enabled.",
                    disallowEmpty(),
                    "conf/hugegraph-server.keystore"
            );

    public static final ConfigOption<String> SSL_KEYSTORE_PASSWORD =
            new ConfigOption<>(
                    "ssl.keystore_password",
                    "The password of the server keystore file " +
                    "when the https protocol is enabled.",
                    null,
                    "hugegraph"
            );

    public static final ConfigOption<Boolean> ENABLE_DYNAMIC_CREATE_DROP =
            new ConfigOption<>(
                    "graphs.enable_dynamic_create_drop",
                    "Whether to enable create or drop graph dynamically.",
                    disallowEmpty(),
                    true
            );
}
