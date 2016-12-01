/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import com.edlio.sis.dao.impl.mongo.SisDaoMongoImpl;
import com.edlio.sis.dao.SisDao;
import com.edlio.sis.model.User;
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
@Path("/user")
public class UserResource {
    
    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());
    
    //Coupling. Not doing dependency injection in this POC.
    private SisDao dao = new SisDaoMongoImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getUser( @PathParam("id") final String id) {
        User user = new User(id);
        User returnObj = (User) dao.get(user);
        if (returnObj == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return JSONMapper.getJson(returnObj);
    }
    
    /**
     * Creates new user object. Not idempotent - new user is generated
     * each time, and new id assigned.
     * @param json JSON representation of the user to create
     * @return JSON representation of the created user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postUser( final String json ) {
        //validate inputs
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        User user = RestModelBuilder.buildUser(jsonRoot);
        dao.insert(user);
        return JSONMapper.getJson(user);
    }
    
    /**
     * updates User object. Idempotent.
     * @param json JSON representation of the user to create
     * @return JSON representation of the updated user
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String putClass( @PathParam("id") final String id, final String json ) {
        
        //validate inputs
        RestServiceValidator.checkString(id);
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        User user = RestModelBuilder.buildUser(jsonRoot);
        user.set_id(id);
        dao.update(user);
        
        return json;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteClass( @PathParam("id") final String id) {
        User user = new User(id);
        dao.delete(user);
    }
}
