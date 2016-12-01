/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * implements ServletContextListener for safe closure of db connection.
 * Determining reasonable to couple to ServletContextListener in this context.
 * @author lance
 */
@WebListener
public class MongoServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MongoConnectionManager.getInstance().getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoConnectionManager.getInstance().closeConnection();
    }
}
