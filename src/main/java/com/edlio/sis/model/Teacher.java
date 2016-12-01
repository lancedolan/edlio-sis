/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.model;

import com.edlio.sis.dao.impl.mongo.model.MongoPersistenceObject;
import java.util.Date;

/**
 * @author lance
 * Domain model for Teacher.
 * 
 */
public class Teacher implements MongoPersistenceObject {
    
    private String _id;
    private Date hireDate;
    
    /**
     * Construct totally transient instance.
     */
    public Teacher() {}
    
    /**
     * Construct transient with id
     * @param id 
     */
    public Teacher(final String id) {
        this._id = id;
        
    }
    
    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    
    
}
