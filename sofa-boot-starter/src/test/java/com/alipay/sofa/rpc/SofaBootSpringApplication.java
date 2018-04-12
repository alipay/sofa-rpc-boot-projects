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
package com.alipay.sofa.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * 
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
@ImportResource({ "classpath*:rpc-sofa-boot-starter-example.xml" })
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SofaBootSpringApplication {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication springApplication = new SpringApplication(SofaBootSpringApplication.class);
        springApplication.run(args);

    }
}
