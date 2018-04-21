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
package com.alipay.sofa.rpc.boot.runtime.convertor;

import com.alipay.sofa.rpc.boot.runtime.adapter.SofaBootAdapterTestApplication;
import com.alipay.sofa.rpc.boot.runtime.binding.RpcBinding;
import com.alipay.sofa.rpc.boot.runtime.converter.BoltBindingConverter;
import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.rpc.boot.runtime.param.RpcBindingParam;
import com.alipay.sofa.runtime.spi.service.BindingConverterContext;
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
 * @version $Id: BoltBindingConvertorTest.java, v 0.1 2018年04月21日 11:20 AM bystander Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class BoltBindingConvertorTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testParamBoltConvertor() {
        BoltBindingConverter convertor = new BoltBindingConverter();

        RpcBindingParam bindingParam = new BoltBindingParam();
        bindingParam.setCallbackRef("callbackRef");
        bindingParam.setRetries(1);
        bindingParam.setTimeout(3);
        bindingParam.setType("callback");
        BindingConverterContext bindingContext = new BindingConverterContext();
        bindingContext.setInBinding(true);
        bindingContext.setApplicationContext(applicationContext);
        bindingContext.setAppClassLoader(this.getClass().getClassLoader());
        RpcBinding binding = convertor.convert(bindingParam, bindingContext);
        Assert.assertNotNull(binding);

        Assert.assertEquals(3, (int) binding.getRpcBindingParam().getTimeout());
        Assert.assertEquals(TestSofaResponseCallback.class, binding.getRpcBindingParam().getCallbackHandler()
            .getClass());
    }
}