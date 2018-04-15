/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.sofa.rpc.samples.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author liangen
 * @version $Id: RestService.java, v 0.1 2018年04月15日 下午5:37 liangen Exp $
 */
@Path("webapi")
public interface RestService {

    @GET
    @Path("/restService/{id}")
    @Produces("application/json")
    String sayRest(@PathParam("id") String string);
}