/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import com.edlio.sis.dao.impl.mongo.SisDaoMongoImpl;
import com.edlio.sis.dao.SisDao;
import com.edlio.sis.model.Student;
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
@Path("/student")
public class StudentResource {
    
    private static final Logger LOG = Logger.getLogger(StudentResource.class.getName());
    
    //Coupling. Not doing dependency injection in this POC.
    private SisDao dao = new SisDaoMongoImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getStudent( @PathParam("id") final String id) {
        Student student = new Student(id);
        Student returnObj = (Student) dao.get(student);
        if (returnObj == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return JSONMapper.getJson(returnObj);
    }
    
    /**
     * Creates new student object. Not idempotent - new student is generated
     * each time, and new id assigned.
     * @param json JSON representation of the student to create
     * @return JSON representation of the created student
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postStudent( final String json ) {
        //validate inputs
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        Student student = RestModelBuilder.buildStudent(jsonRoot);
        dao.insert(student);
        return JSONMapper.getJson(student);
    }
    
    /**
     * updates Student object. Idempotent.
     * @param json JSON representation of the student to create
     * @return JSON representation of the updated student
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String putClass( @PathParam("id") final String id, final String json ) {
        
        //validate inputs
        RestServiceValidator.checkString(id);
        RestServiceValidator.checkString(json);
        
        SisJsonObject jsonRoot = new SisJsonObject(json);
        Student student = RestModelBuilder.buildStudent(jsonRoot);
        student.set_id(id);
        dao.update(student);
        
        return json;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteClass( @PathParam("id") final String id) {
        Student student = new Student(id);
        dao.delete(student);
    }
}
