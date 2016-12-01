/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao;

import com.edlio.sis.dao.impl.mongo.model.MongoPersistenceObject;

/**
 *
 * @author lance
 */
public interface SisDao {
    
    public String insert(MongoPersistenceObject object);
    
    public void update(MongoPersistenceObject object);
    
    public void delete(MongoPersistenceObject object);
    
    /**
     * 
     * @param object A transient object which contains the ID of the object to get.
     * Other attributes of the object are ignored.
     * 
     * @return The Object from the database.
     */
    public Object get(MongoPersistenceObject object);
    
}
