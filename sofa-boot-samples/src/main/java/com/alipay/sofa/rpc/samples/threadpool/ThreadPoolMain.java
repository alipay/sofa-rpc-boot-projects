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
package com.alipay.sofa.rpc.samples.threadpool;

import com.alipay.sofa.rpc.samples.threadpool.bean.ThreadPoolService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author liangen
 * @version $Id: ThreadPoolMain.java, v 0.1 2018年04月13日 下午2:09 liangen Exp $
 */
public class ThreadPoolMain {

    public void start(ApplicationContext applicationContext) {
        ThreadPoolService threadPoolService = (ThreadPoolService) applicationContext
            .getBean("threadPoolServiceReference");

        System.out.println(threadPoolService.sayThreadPool("threadPool"));

    }
}