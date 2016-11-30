/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.model;

/**
 * @author lance
 * Domain model for Class, as in "school class."
 * 
 */
public class Class {
    
    private int id;
    private String title;
    private int period;

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
