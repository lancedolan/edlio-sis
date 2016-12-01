/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.model;

import org.json.JSONObject;

/**
 * @author lance
 * Domain model for class, as in "school class."
 * Named SisClass to avoid collision with java.lang.Class.
 * 
 */
public class SisClass {
    
    private int id;
    private String title;
    private int period;

    /**
     * JSON containing attributes necessary to construct Class
     * @param jsonRoot 
     */
    public SisClass(final JSONObject jsonRoot) {
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
