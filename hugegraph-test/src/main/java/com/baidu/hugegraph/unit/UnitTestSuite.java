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

package com.baidu.hugegraph.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.baidu.hugegraph.unit.core.SystemSchemaStoreTest;
import com.baidu.hugegraph.unit.util.RateLimiterTest;
import com.baidu.hugegraph.unit.cache.CacheManagerTest;
import com.baidu.hugegraph.unit.cache.CacheTest;
import com.baidu.hugegraph.unit.cache.CachedGraphTransactionTest;
import com.baidu.hugegraph.unit.cache.CachedSchemaTransactionTest;
import com.baidu.hugegraph.unit.cache.RamTableTest;
import com.baidu.hugegraph.unit.cassandra.CassandraTest;
import com.baidu.hugegraph.unit.core.AnalyzerTest;
import com.baidu.hugegraph.unit.core.BackendMutationTest;
import com.baidu.hugegraph.unit.core.BackendStoreInfoTest;
import com.baidu.hugegraph.unit.core.ConditionQueryFlattenTest;
import com.baidu.hugegraph.unit.core.ConditionTest;
import com.baidu.hugegraph.unit.core.DataTypeTest;
import com.baidu.hugegraph.unit.core.DirectionsTest;
import com.baidu.hugegraph.unit.core.ExceptionTest;
import com.baidu.hugegraph.unit.core.LocksTableTest;
import com.baidu.hugegraph.unit.core.PageStateTest;
import com.baidu.hugegraph.unit.core.QueryTest;
import com.baidu.hugegraph.unit.core.RangeTest;
import com.baidu.hugegraph.unit.core.RolePermissionTest;
import com.baidu.hugegraph.unit.core.RowLockTest;
import com.baidu.hugegraph.unit.core.SecurityManagerTest;
import com.baidu.hugegraph.unit.core.SerialEnumTest;
import com.baidu.hugegraph.unit.core.TraversalUtilTest;
import com.baidu.hugegraph.unit.id.EdgeIdTest;
import com.baidu.hugegraph.unit.id.IdTest;
import com.baidu.hugegraph.unit.id.IdUtilTest;
import com.baidu.hugegraph.unit.id.SplicingIdGeneratorTest;
import com.baidu.hugegraph.unit.mysql.MysqlUtilTest;
import com.baidu.hugegraph.unit.mysql.WhereBuilderTest;
import com.baidu.hugegraph.unit.rocksdb.RocksDBCountersTest;
import com.baidu.hugegraph.unit.rocksdb.RocksDBSessionTest;
import com.baidu.hugegraph.unit.rocksdb.RocksDBSessionsTest;
import com.baidu.hugegraph.unit.serializer.BinaryBackendEntryTest;
import com.baidu.hugegraph.unit.serializer.BinaryScatterSerializerTest;
import com.baidu.hugegraph.unit.serializer.BinarySerializerTest;
import com.baidu.hugegraph.unit.serializer.BytesBufferTest;
import com.baidu.hugegraph.unit.serializer.SerializerFactoryTest;
import com.baidu.hugegraph.unit.serializer.StoreSerializerTest;
import com.baidu.hugegraph.unit.serializer.TableBackendEntryTest;
import com.baidu.hugegraph.unit.serializer.TextBackendEntryTest;
import com.baidu.hugegraph.unit.util.CompressUtilTest;
import com.baidu.hugegraph.unit.util.JsonUtilTest;
import com.baidu.hugegraph.unit.util.RateLimiterTest;
import com.baidu.hugegraph.unit.util.StringEncodingTest;
import com.baidu.hugegraph.unit.util.VersionTest;
import com.baidu.hugegraph.unit.util.collection.CollectionFactoryTest;
import com.baidu.hugegraph.unit.util.collection.IdSetTest;
import com.baidu.hugegraph.unit.util.collection.Int2IntsMapTest;
import com.baidu.hugegraph.unit.util.collection.IntMapTest;
import com.baidu.hugegraph.unit.util.collection.IntSetTest;
import com.baidu.hugegraph.unit.util.collection.ObjectIntMappingTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    /* cache */
    CacheTest.RamCacheTest.class,
    CacheTest.OffheapCacheTest.class,
    CacheTest.LevelCacheTest.class,
    CachedSchemaTransactionTest.class,
    CachedGraphTransactionTest.class,
    CacheManagerTest.class,
    RamTableTest.class,

    /* types */
    DataTypeTest.class,
    DirectionsTest.class,
    SerialEnumTest.class,

    /* id */
    IdTest.class,
    EdgeIdTest.class,
    IdUtilTest.class,
    SplicingIdGeneratorTest.class,

    /* core */
    LocksTableTest.class,
    RowLockTest.class,
    AnalyzerTest.class,
    BackendMutationTest.class,
    ConditionTest.class,
    ConditionQueryFlattenTest.class,
    QueryTest.class,
    RangeTest.class,
    SecurityManagerTest.class,
    RolePermissionTest.class,
    ExceptionTest.class,
    BackendStoreInfoTest.class,
    TraversalUtilTest.class,
    PageStateTest.class,
    SystemSchemaStoreTest.class,

    /* serializer */
    BytesBufferTest.class,
    SerializerFactoryTest.class,
    TextBackendEntryTest.class,
    TableBackendEntryTest.class,
    BinaryBackendEntryTest.class,
    BinarySerializerTest.class,
    BinaryScatterSerializerTest.class,
    StoreSerializerTest.class,

    /* cassandra */
    CassandraTest.class,

    /* mysql */
    MysqlUtilTest.class,
    WhereBuilderTest.class,

    /* rocksdb */
    RocksDBSessionsTest.class,
    RocksDBSessionTest.class,
    RocksDBCountersTest.class,

    /* utils */
    VersionTest.class,
    JsonUtilTest.class,
    StringEncodingTest.class,
    CompressUtilTest.class,
    RateLimiterTest.FixedTimerWindowRateLimiterTest.class,
    RateLimiterTest.FixedWatchWindowRateLimiterTest.class,

    /* utils.collection */
    CollectionFactoryTest.class,
    ObjectIntMappingTest.class,
    Int2IntsMapTest.class,
    IdSetTest.class,
    IntMapTest.class,
    IntSetTest.class
})
public class UnitTestSuite {
}
