/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.model;

import com.edlio.sis.dao.impl.mongo.model.MongoPersistenceObject;

/**
 * @author lance
 * Domain model for school.
 * 
 */
public class School implements MongoPersistenceObject {
    
    private String _id;
    private String name;
    private String streetAddress;
    private String zip;
    private String state;
    private SchoolType schoolType;
    
    public enum SchoolType {
        elementary, middle, high, vocational
    }

    /**
     * Construct totally transient instance.
     */
    public School() {}
    
    /**
     * Construct transient with id
     * @param id 
     */
    public School(final String id) {
        this._id = id;
        
    }
    
    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }

  
    
    
    
}
