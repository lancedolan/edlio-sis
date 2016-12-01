/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo;

import com.edlio.sis.dao.SisDao;
import com.edlio.sis.rest.JSONMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

/**
 *
 * @author lance
 */
final public class SisDaoMongoImpl implements SisDao {
    
    //anti-pattern, preferabbly in a config file or something
    private static final String DATABASE_NAME = "sis";
    
    //As noted in readme, I'm not solving the dependency injection
    //concern in this POC. Gladly coupling here.
    final private ClassToCollectionMapper collectionMapper = new ClassToCollectionMapper();
            
    @Override
    public void save(final Object object) {
        MongoClient client = MongoConnectionManager.getInstance().getConnection();
        MongoDatabase db = client.getDatabase("sis");
        final String collectionName = 
                collectionMapper.getMongoCollectionName(object);
        MongoCollection collection = db.getCollection(collectionName);
        //serializing and then parsing to get implicit mapping...
        //Convenient but perfomance-costly
        final String objectJson = JSONMapper.getJson(object);
        collection.insertOne( JSON.parse(objectJson) );
        
    }
    
    
}
