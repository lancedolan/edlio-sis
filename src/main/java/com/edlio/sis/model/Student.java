/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.model;

import com.edlio.sis.dao.impl.mongo.model.MongoPersistenceObject;

/**
 * @author lance
 * Domain model for Student.
 * 
 */
public class Student implements MongoPersistenceObject {
    
    private String _id;
    private String grade;
    private float gpa;
    
    /**
     * Construct totally transient instance.
     */
    public Student() {}
    
    /**
     * Construct transient with id
     * @param id 
     */
    public Student(final String id) {
        this._id = id;
        
    }
    
    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    
    
}
