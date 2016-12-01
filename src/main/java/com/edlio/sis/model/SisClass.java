/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.model;

import com.edlio.sis.dao.impl.mongo.model.MongoPersistenceObject;
import com.edlio.sis.rest.SisJsonObject;
import org.json.JSONObject;

/**
 * @author lance
 * Domain model for class, as in "school class."
 * Named SisClass to avoid collision with java.lang.Class.
 * 
 */
public class SisClass implements MongoPersistenceObject {
    
    private String _id;
    private String title;
    private int period;

    /**
     * Construct totally transient instance.
     */
    public SisClass() {}
    
    /**
     * Construct Transient with all data in json.
     * @param json JSON containing attributes necessary to construct Class
     */
    public SisClass(final SisJsonObject json) {
        this._id = json.getStringOrNull("_id");
        this.title = json.getStringOrNull("title");
        this.period = json.getInt("period");
    }
    
    /**
     * Construct transient with id
     * @param jsonRoot 
     */
    public SisClass(final String id) {
        this._id = id;
        
    }
    
    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    
    
    
}
