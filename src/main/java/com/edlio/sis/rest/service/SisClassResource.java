/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import com.edlio.sis.dao.impl.mongo.SisDaoMongoImpl;
import com.edlio.sis.dao.SisDao;
import com.edlio.sis.model.SisClass;
import com.edlio.sis.rest.JSONMapper;
import com.edlio.sis.rest.SisJsonObject;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
public class SisClassResource {
    
    private static final Logger LOG = Logger.getLogger(SisClassResource.class.getName());
    
    //Coupling. Not doing dependency injection in this POC.
    private SisDao dao = new SisDaoMongoImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getClass( @PathParam("id") final String id) {
        LOG.info("Getting class with id " + id);
        SisClass sisClassnew = new SisClass(id);
        Object returnObj = dao.get(sisClassnew);
        return JSONMapper.getJson(returnObj);
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
        //validate inputs
        RestServiceHelper.checkString(json);
        
        LOG.fine("Received Post Class with json: \n"+json);
        SisJsonObject jsonRoot = new SisJsonObject(json);
        SisClass sisClass = new SisClass(jsonRoot);
        LOG.fine("Constructed SisClass from json:");
        LOG.fine("id:" + sisClass.get_id());
        LOG.fine("title:" + sisClass.getTitle());
        LOG.fine("periord:" + sisClass.getPeriod());
        dao.insert(sisClass);
        return json;
    }
    
    /**
     * updates new class object. Idempotent.
     * @param json JSON representation of the class to create
     * @return JSON representation of the created class
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String putClass( @PathParam("id") final String id, final String json ) {
        
        //validate inputs
        RestServiceHelper.checkString(id);
        RestServiceHelper.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        SisClass sisClass = new SisClass(jsonRoot);
        sisClass.set_id(id);
        dao.update(sisClass);
        
        return json;
    }
}
