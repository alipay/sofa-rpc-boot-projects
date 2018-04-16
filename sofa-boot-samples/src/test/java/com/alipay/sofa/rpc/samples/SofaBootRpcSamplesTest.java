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
package com.alipay.sofa.rpc.samples;

import com.alipay.sofa.rpc.samples.direct.DirectSample;
import com.alipay.sofa.rpc.samples.dubbo.DubboSample;
import com.alipay.sofa.rpc.samples.filter.FilterSample;
import com.alipay.sofa.rpc.samples.generic.GenericSample;
import com.alipay.sofa.rpc.samples.invoke.InvokeSample;
import com.alipay.sofa.rpc.samples.rest.RestSample;
import com.alipay.sofa.rpc.samples.threadpool.ThreadPoolSample;
import com.alipay.sofa.test.runner.SofaBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
@RunWith(SofaBootRunner.class)
@SpringBootTest(classes = SofaBootRpcSamplesApplication.class)
public class SofaBootRpcSamplesTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testALl() throws InterruptedException {
        new InvokeSample().start(applicationContext);
        new DirectSample().start(applicationContext);
        new GenericSample().start(applicationContext);
        new FilterSample().start(applicationContext);
        new ThreadPoolSample().start(applicationContext);
        new RestSample().start(applicationContext);
        new DubboSample().start(applicationContext);
    }
}