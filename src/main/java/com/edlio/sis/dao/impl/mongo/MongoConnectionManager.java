/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo;

import com.google.common.base.Strings;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.logging.Logger;

/**
 *
 * VERY rudimentary connection manager to get through this POC. 
 * 
 * Singleton is necessary evil to give access of self to ServletContextListener, 
 * without bringing in some dependency injection framework OR coupling all
 * layers of the application to the ServletContext as a cross-cutting concern
 * (ie passing ServletContext around everywhere)
 * 
 * 
 * @author lance
 */
class MongoConnectionManager {

    private static final Logger LOG = Logger.getLogger(MongoConnectionManager.class.getName());

    final String connectionUri;
    MongoClient connection;

    //anti-pattern
    private static MongoConnectionManager instance;

    private MongoConnectionManager() {
        connectionUri = System.getenv("MONGODB_URI");
        if (Strings.isNullOrEmpty(connectionUri)) {
            //At times like this, we fail hard as possible
            //And loud as possible.
            LOG.severe("No valid connection to db.");
            throw new IllegalStateException();
        }
    }

    public static MongoConnectionManager getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (MongoConnectionManager.class) {
                if (instance == null) {
                    instance = new MongoConnectionManager();
                }
            }
        }
        return instance;

    }

    synchronized MongoClient getConnection() {
        if (connection == null) {
            connection = new MongoClient(new MongoClientURI(connectionUri));
        }
        return connection;
    }

    synchronized void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            LOG.severe("Something went wrong while attempting to close the MONGO db connection:"
                    + e.getLocalizedMessage());
        }
    }

}
