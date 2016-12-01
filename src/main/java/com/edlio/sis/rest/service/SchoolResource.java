/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import com.edlio.sis.dao.impl.mongo.SisDaoMongoImpl;
import com.edlio.sis.dao.SisDao;
import com.edlio.sis.model.School;
import com.edlio.sis.rest.JSONMapper;
import com.edlio.sis.rest.SisJsonObject;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lance
 */
@Path("/school")
public class SchoolResource {
    
    private static final Logger LOG = Logger.getLogger(SchoolResource.class.getName());
    
    //Coupling. Not doing dependency injection in this POC.
    private SisDao dao = new SisDaoMongoImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getSchool( @PathParam("id") final String id) {
        School school = new School(id);
        School returnObj = (School) dao.get(school);
        if (returnObj == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return JSONMapper.getJson(returnObj);
    }
    
    /**
     * Creates new school object. Not idempotent - new school is generated
     * each time, and new id assigned.
     * @param json JSON representation of the school to create
     * @return JSON representation of the created school
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postSchool( final String json ) {
        //validate inputs
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        School school = RestModelBuilder.buildSchool(jsonRoot);
        dao.insert(school);
        return JSONMapper.getJson(school);
    }
    
    /**
     * updates School object. Idempotent.
     * @param json JSON representation of the school to create
     * @return JSON representation of the updated school
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String putClass( @PathParam("id") final String id, final String json ) {
        
        //validate inputs
        RestServiceValidator.checkString(id);
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        School school = RestModelBuilder.buildSchool(jsonRoot);
        school.set_id(id);
        dao.update(school);
        
        return json;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteClass( @PathParam("id") final String id) {
        School school = new School(id);
        dao.delete(school);
    }
}
