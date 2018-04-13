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
package com.alipay.sofa.rpc.register.holder;

import com.alipay.sofa.rpc.common.SofaBootRpcRuntimeException;
import com.alipay.sofa.rpc.filter.Filter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * Holds the filter class or filter id that parses the XML.
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class RpcFilterHolder {

    private static List<String> filterIds     = new CopyOnWriteArrayList<String>();

    private static List<String> filterClasses = new CopyOnWriteArrayList<String>();

    private static boolean      alreadyLoad   = false;
    private static final Object locdLock      = new Object();

    private static List<Filter> filters       = new CopyOnWriteArrayList<Filter>();

    public static void addFilterId(String filterId) {
        if (StringUtils.hasText(filterId)) {
            filterIds.add(filterId);
        }
    }

    public static void addFilterClass(String filterClass) {
        if (StringUtils.hasText(filterClass)) {
            filterClasses.add(filterClass);
        }
    }

    public static List<Filter> getFilters(ApplicationContext applicationContext) {

        if (applicationContext != null) {
            if (alreadyLoad == false) {
                synchronized (locdLock) {
                    if (alreadyLoad == false) {
                        loadFilters(applicationContext);
                        alreadyLoad = true;
                    }

                }

            }

            return filters;

        } else {
            throw new SofaBootRpcRuntimeException("the applicationContext should not be null");
        }
    }

    public static void loadFilters(ApplicationContext applicationContext) {
        for (String filterId : filterIds) {
            filters.add((applicationContext.getBean(filterId, Filter.class)));
        }
        for (String clazz : filterClasses) {
            Class filterClass = null;
            try {
                filterClass = Class.forName(clazz);
            } catch (ClassNotFoundException e) {
                throw new SofaBootRpcRuntimeException("can not find filter class " + filterClass + " ", e);
            }
            if (Filter.class.isAssignableFrom(filterClass)) {
                try {
                    filters.add((Filter) filterClass.newInstance());
                } catch (Exception e) {
                    throw new SofaBootRpcRuntimeException("error happen when create instance of " + filterClass + " ",
                        e);
                }
            } else {
                throw new SofaBootRpcRuntimeException(
                    "the class of " + clazz + " should be  a subclass of Filter");
            }
        }
    }
}