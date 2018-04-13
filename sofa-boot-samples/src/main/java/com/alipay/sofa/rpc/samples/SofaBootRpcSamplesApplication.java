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

import com.alipay.sofa.rpc.samples.direct.DirectMain;
import com.alipay.sofa.rpc.samples.filter.FilterMain;
import com.alipay.sofa.rpc.samples.generic.bean.GenericMain;
import com.alipay.sofa.rpc.samples.invoke.InvokeMain;
import com.alipay.sofa.rpc.samples.threadpool.ThreadPoolMain;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
@ImportResource({ "classpath*:rpc-sofa-boot-starter-samples.xml" })
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SofaBootRpcSamplesApplication {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication springApplication = new SpringApplication(SofaBootRpcSamplesApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);

        new InvokeMain().start(applicationContext);
        new DirectMain().start(applicationContext);
        new GenericMain().start(applicationContext);
        new FilterMain().start(applicationContext);
        new ThreadPoolMain().start(applicationContext);

    }
}
