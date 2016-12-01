/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.model;

import com.edlio.sis.dao.impl.mongo.model.MongoPersistenceObject;
import java.util.Date;

/**
 * @author lance
 * Domain model for User.
 * 
 */
public class User implements MongoPersistenceObject {
    
    private String _id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;
    
    public enum Gender {
        male , female
    }
    
    /**
     * Construct totally transient instance.
     */
    public User() {}
    
    /**
     * Construct transient with id
     * @param id 
     */
    public User(final String id) {
        this._id = id;
        
    }
    
    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    

    
}
