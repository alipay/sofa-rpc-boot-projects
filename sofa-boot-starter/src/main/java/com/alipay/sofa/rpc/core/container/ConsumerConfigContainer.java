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
package com.alipay.sofa.rpc.core.container;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.runtime.spi.binding.Binding;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class ConsumerConfigContainer {

    private final static ConcurrentMap<Binding, ConsumerConfig> consumerConfigMap = new ConcurrentHashMap<Binding, ConsumerConfig>();

    public static void addConsumerConfig(Binding binding, ConsumerConfig consumerConfig) {
        if (binding != null) {
            consumerConfigMap.put(binding, consumerConfig);
        }
    }

    public static void removeAndUnReferCounsumerConfig(Binding binding) {
        if (binding != null && consumerConfigMap.containsKey(binding)) {
            ConsumerConfig consumerConfig = consumerConfigMap.remove(binding);
            if (consumerConfig != null) {
                consumerConfig.unRefer();
            }
        }
    }

    public static boolean contains(Binding binding) {
        return consumerConfigMap.containsKey(binding);
    }

    public static ConsumerConfig getConsumerConfig(Binding binding) {
        return consumerConfigMap.get(binding);
    }

}