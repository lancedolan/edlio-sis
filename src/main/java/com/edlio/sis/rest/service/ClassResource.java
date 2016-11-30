/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author lance
 */
@Path("class")
public class ClassResource {
    
    private static final Logger LOG = Logger.getLogger(ClassResource.class.getName());
    
    @Path("{id}")
    public String getClass( @PathParam("id") final Long id) {
        LOG.info("Getting class with id " + id);
        return "{ 'id' : " + id + "}" ;
    }
    
}
