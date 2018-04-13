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
package com.alipay.sofa.rpc.register.util;

import org.springframework.util.StringUtils;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
public class SofaBootRpcParserUtil {

    public static Integer parseInteger(String string) {

        if (StringUtils.hasText(string)) {
            return Integer.valueOf(string);
        }

        return null;
    }

    public static Boolean parseBoolean(String string) {
        if (StringUtils.hasText(string)) {
            return Boolean.valueOf(string);
        }

        return null;
    }

    public static Long parseLong(String string) {
        if (StringUtils.hasText(string)) {
            return Long.valueOf(string);
        }

        return null;
    }

    public static Double parseDuoble(String string) {
        if (StringUtils.hasText(string)) {
            return Double.valueOf(string);
        }

        return null;
    }
}