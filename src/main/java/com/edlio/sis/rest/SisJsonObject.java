/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.rest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Extend org.json.JSONOBject with option to not throw exception when desired 
 * String isn't available.
 * @author lance
 */
public class SisJsonObject extends JSONObject {
    
    public SisJsonObject() {
        super();
    }
    
    public SisJsonObject(final String json) {
        super(json);
    }
    
    public String getStringOrNull(String key) throws JSONException {
        if (this.has(key)) {
            return super.getString(key);
        }
        return null;
    }
    
}
