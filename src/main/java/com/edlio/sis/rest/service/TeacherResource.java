/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import com.edlio.sis.dao.impl.mongo.SisDaoMongoImpl;
import com.edlio.sis.dao.SisDao;
import com.edlio.sis.model.Teacher;
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
@Path("/teacher")
public class TeacherResource {
    
    private static final Logger LOG = Logger.getLogger(TeacherResource.class.getName());
    
    //Coupling. Not doing dependency injection in this POC.
    private SisDao dao = new SisDaoMongoImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getTeacher( @PathParam("id") final String id) {
        Teacher teacher = new Teacher(id);
        Teacher returnObj = (Teacher) dao.get(teacher);
        if (returnObj == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return JSONMapper.getJson(returnObj);
    }
    
    /**
     * Creates new teacher object. Not idempotent - new teacher is generated
     * each time, and new id assigned.
     * @param json JSON representation of the teacher to create
     * @return JSON representation of the created teacher
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postTeacher( final String json ) {
        //validate inputs
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        Teacher sisClass = RestModelBuilder.buildTeacher(jsonRoot);
        dao.insert(sisClass);
        return json;
    }
    
    /**
     * updates Teacher object. Idempotent.
     * @param json JSON representation of the teacher to create
     * @return JSON representation of the updated teacher
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String putClass( @PathParam("id") final String id, final String json ) {
        
        //validate inputs
        RestServiceValidator.checkString(id);
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        Teacher teacher = RestModelBuilder.buildTeacher(jsonRoot);
        teacher.set_id(id);
        dao.update(teacher);
        
        return json;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteClass( @PathParam("id") final String id) {
        Teacher teacher = new Teacher(id);
        dao.delete(teacher);
    }
}
