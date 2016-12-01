/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo.model;

/**
 * Simple interface which, when implemented by a domain POJO, becomes 
 * persistable by the mongo dao impl
 * @author lance
 */
public interface MongoPersistenceObject {
    
    public String get_id();
    public void set_id(String id);
    
}
