/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import com.edlio.sis.dao.impl.mongo.SisDaoMongoImpl;
import com.edlio.sis.dao.SisDao;
import com.edlio.sis.model.SisClass;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author lance
 */
@Path("/class")
public class ClassResource {
    
    private static final Logger LOG = Logger.getLogger(ClassResource.class.getName());
    
    //Coupling. Not doing dependency injection in this POC.
    private SisDao dao = new SisDaoMongoImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getClass( @PathParam("id") final Long id) {
        LOG.info("Getting class with id " + id);
        return "{ 'id' : " + id + "" ;
    }
    
    /**
     * Creates new class object. Not idempotent - new class is generated
     * each time, and new id assigned.
     * @param json JSON representation of the class to create
     * @return JSON representation of the created class
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postClass( final String json ) {
        JSONObject jsonRoot = new JSONObject(json);
        SisClass sisClass = new SisClass(jsonRoot);
        dao.save(sisClass);
        return json;
    }
}
