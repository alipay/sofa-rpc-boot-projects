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
package com.alipay.sofa.rpc.lazy;

import com.alipay.sofa.test.runner.SofaBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei.Liangen</a>
 * @version $Id: LazyTest.java, v 0.1 2018年04月26日 下午5:58 LiWei.Liangen Exp $
 */
@RunWith(SofaBootRunner.class)
@SpringBootTest(classes = LazySofaBootRpcApplication.class)
public class LazyTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testLazy() {
        LazyService lazyServiceBolt = (LazyService) applicationContext.getBean("lazyServiceReferenceBolt");
        LazyService lazyServiceDubbo = (LazyService) applicationContext.getBean("lazyServiceReferenceDubbo");

        System.out.println(lazyServiceBolt.sayLazy("lazy_bolt"));
        System.out.println(lazyServiceDubbo.sayLazy("lazy_dubbo"));

    }
}