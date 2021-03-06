package com.edlio.sis.rest.service;

import com.google.common.base.Strings;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author lance
 */
class RestServiceValidator {
    
    private static final Logger LOG = Logger.getLogger(RestServiceValidator.class.getName());
    
    /**
     * Check if the Long is not null. If it is, throw BAD_REQUEST to client.
     * @param param 
     */
    static void checkLong(final Long param) {
        if (param==null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    /**
     * Check if the String is not null or blank. If it is, throw BAD_REQUEST to client.
     * @param param 
     */
    static void checkString(final String param) {
        if (Strings.isNullOrEmpty(param)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
}
