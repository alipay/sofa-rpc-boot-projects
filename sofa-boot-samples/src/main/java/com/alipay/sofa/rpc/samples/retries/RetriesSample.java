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
package com.alipay.sofa.rpc.samples.retries;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei.Liangen</a>
 * @version $Id: RetrySample.java, v 0.1 2018年04月26日 下午3:09 LiWei.Liangen Exp $
 */
public class RetriesSample {

    public void start(ApplicationContext applicationContext) throws InterruptedException {

        RetriesService retriesServiceBolt = (RetriesService) applicationContext.getBean("retriesServiceReferenceBolt");
        RetriesService retriesServiceDubbo = (RetriesService) applicationContext
            .getBean("retriesServiceReferenceDubbo");

        String resultBolt = retriesServiceBolt.sayRetry("retries_bolt");
        String resultDubbo = retriesServiceDubbo.sayRetry("retries_dubbo");

        System.out.println(resultBolt);
        System.out.println(resultDubbo);
        System.out.println(retriesServiceBolt.getCount());

    }
}