/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.config;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class ZookeeperConfigTest {

    @Test
    public void test() {
        String config = "zookeeper://11.166.1.35:2181?aaa=111&rrr=666&file=/host/zk";

        ZookeeperConfiger.parseConfig(config);

        Assert.assertEquals("111", ZookeeperConfiger.getParamValue("aaa"));
        Assert.assertEquals("666", ZookeeperConfiger.getParamValue("rrr"));
        Assert.assertEquals("11.166.1.35:2181", ZookeeperConfiger.getAddress());
        Assert.assertEquals("/host/zk", ZookeeperConfiger.getFile());
    }
}