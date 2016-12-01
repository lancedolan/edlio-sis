/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest.service;

import com.edlio.sis.model.School;
import com.edlio.sis.model.SisClass;
import com.edlio.sis.model.Student;
import com.edlio.sis.model.Teacher;
import com.edlio.sis.model.User;
import com.edlio.sis.rest.SisJsonObject;
import java.util.Date;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Used for building domain objects based on REST Json input.
 * Holds the concern for mapping JSON attributes into Java POJO attributes.
 * This is an outdated pattern, containing duplication.
 * Would use annotations on model to Map to JSON attributes.
 * Don't have time to tinker with the Jackson configuration.
 * 
 * @author lance
 */
public class RestModelBuilder {
    
    /**
     * 
     * @param json
     * @return Built SisClass
     */
    static SisClass buildSisClass(final SisJsonObject json ) {
        SisClass sisClass = new SisClass();
        
        sisClass.set_id(json.getStringOrNull("_id"));
        sisClass.setPeriod(json.getInt("period"));
        sisClass.setTitle(json.getStringOrNull("title"));
        
        return sisClass;
    }
    
    /**
     * 
     * @param json
     * @return Built SisClass
     */
    static School buildSchool(final SisJsonObject json ) {
        School school = new School();
        
        school.set_id(json.getStringOrNull("_id"));
        school.setName(json.getStringOrNull("name"));
        school.setStreetAddress(json.getStringOrNull("street_address"));
        school.setZip(json.getStringOrNull("zip"));
        school.setState(json.getStringOrNull("state"));
        
        if (json.has("school_type")) {
            switch(json.getString("school_type")) {
                case "elementary":
                    school.setSchoolType(School.SchoolType.elementary);
                    break;
                case "middle":
                    school.setSchoolType( School.SchoolType.middle);
                    break;
                case "high":
                    school.setSchoolType( School.SchoolType.high);
                    break;
                case "vocational":
                    school.setSchoolType( School.SchoolType.vocational);
                    break;
                default:
                    //TODO: inform user reason of bad request
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }
        return school;
    }
    
    static Student buildStudent(final SisJsonObject json) {
        Student student = new Student();
        
        student.set_id(json.getStringOrNull("_id"));
        student.setGpa(json.getInt("gpa"));
        student.setGrade(json.getString("grade"));
        
        return student;
    }
    
    static User buildUser(final SisJsonObject json) {
        User user = new User();
        
        user.set_id(json.getStringOrNull("_id"));
        user.setFirstName(json.getStringOrNull("first_name"));
        user.setLastName(json.getStringOrNull("last_name"));
        
        try {
            user.setGender(User.Gender.valueOf(json.getStringOrNull("gender")));
        } catch (IllegalArgumentException e) {
            //TODO: inform user reason of bad request
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        long epochBirthday = json.getLong("birth_date");
        user.setBirthDate(new Date(epochBirthday));
        
        return user;
    }
    
    static Teacher buildTeacher(final SisJsonObject json) {
        Teacher teacher = new Teacher();
        
        teacher.set_id(json.getStringOrNull("_id"));
        long hireDate = json.getLong("hire_date");
        teacher.setHireDate(new Date(hireDate));
        
        return teacher;
    }
    
}
