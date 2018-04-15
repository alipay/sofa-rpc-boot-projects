/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.sofa.rpc.samples.rest;

/**
 *
 * @author liangen
 * @version $Id: RestServiceImpl.java, v 0.1 2018年04月15日 下午5:38 liangen Exp $
 */
public class RestServiceImpl implements RestService {
    @Override
    public String sayRest(String string) {
        return "rest";
    }
}