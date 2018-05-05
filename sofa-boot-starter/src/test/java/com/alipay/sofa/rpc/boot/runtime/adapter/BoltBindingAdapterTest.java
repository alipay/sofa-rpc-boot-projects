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
package com.alipay.sofa.rpc.boot.runtime.adapter;

import com.alipay.sofa.rpc.boot.container.ProviderConfigContainer;
import com.alipay.sofa.rpc.boot.runtime.binding.BoltBinding;
import com.alipay.sofa.rpc.boot.runtime.binding.RpcBinding;
import com.alipay.sofa.rpc.boot.runtime.demo.DemoService;
import com.alipay.sofa.rpc.boot.runtime.demo.impl.DemoServiceImpl;
import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.rpc.boot.runtime.param.RpcBindingParam;
import com.alipay.sofa.runtime.service.component.impl.ServiceImpl;
import com.alipay.sofa.runtime.spi.SofaFrameworkHolder;
import com.alipay.sofa.runtime.spi.binding.BindingAdapter;
import com.alipay.sofa.runtime.spi.binding.Contract;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * @author bystander
 * @version $Id: BoltBindingAdapterTest.java, v 0.1 2018年04月21日 10:36 AM bystander Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class BoltBindingAdapterTest {

    @Autowired
    private ApplicationContext      applicationContext;

    @Autowired
    private ProviderConfigContainer providerConfigContainer;

    @Test
    public void testPreOutBinding() {

        BindingAdapter adapter = new BoltBindingAdapter();

        final DemoService target = new DemoServiceImpl();
        Contract contract = new ServiceImpl("uniqId", DemoService.class, target);
        RpcBindingParam rpcBindingParam = new BoltBindingParam();

        RpcBinding binding = new BoltBinding(rpcBindingParam, applicationContext, false);

        adapter.preOutBinding(contract, binding, target,
            SofaFrameworkHolder.getSofaFramework().getSofaRuntimeContext("test"));

        String serviceUniqName = providerConfigContainer.createUniqueName(contract, binding);

        Assert.assertNotNull(providerConfigContainer.getProviderConfig(serviceUniqName));

    }

    @Test
    public void testOutBinding() {

        BindingAdapter adapter = new BoltBindingAdapter();

        final DemoService target = new DemoServiceImpl();
        Contract contract = new ServiceImpl("uniqId", DemoService.class, target);
        RpcBindingParam rpcBindingParam = new BoltBindingParam();

        RpcBinding binding = new BoltBinding(rpcBindingParam, applicationContext, false);

        final SofaRuntimeContext sofaRuntimeContext = SofaFrameworkHolder.getSofaFramework().getSofaRuntimeContext(
            "test");

        adapter.preOutBinding(contract, binding, target, sofaRuntimeContext);

        boolean outBindingResult = (Boolean) adapter.outBinding(contract, binding, target,
            sofaRuntimeContext);

        Assert.assertTrue(outBindingResult);

    }
}