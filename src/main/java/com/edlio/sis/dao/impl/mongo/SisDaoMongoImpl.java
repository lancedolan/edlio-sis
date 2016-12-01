/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo;

import com.edlio.sis.dao.SisDao;
import com.edlio.sis.dao.impl.mongo.model.MongoPersistenceObject;
import com.edlio.sis.rest.JSONMapper;
import com.edlio.sis.rest.service.SisClassResource;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.UpdateResult;


/**
 *
 * @author lance
 */
final public class SisDaoMongoImpl implements SisDao {
    
    private static final Logger LOG = Logger.getLogger(SisClassResource.class.getName());
    
    //anti-pattern, preferabbly in a config file or something
    private static final String DATABASE_NAME = "heroku_nkfdk7fm";
    
    //As noted in readme, I'm not solving the dependency injection
    //concern in this POC. Gladly coupling here.
    final private ClassToCollectionMapper collectionMapper = new ClassToCollectionMapper();
    final private MongoDocumentMapper documentMapper = new MongoDocumentMapper();
    
    /**
     * 
     * @param object
     * @return the correct mongo collection based on Object type
     */
    private MongoCollection getCollection(final MongoPersistenceObject object) {
        MongoClient client = MongoConnectionManager.getInstance().getConnection();
        MongoDatabase db = client.getDatabase(DATABASE_NAME);
        final String collectionName = 
                collectionMapper.getMongoCollectionName(object);
        return db.getCollection(collectionName);
    }
    
    /**
     * Will set the id on object.
     * @param object
     * @return 
     */
    @Override
    public String insert(final MongoPersistenceObject object) {
        MongoCollection collection = getCollection(object);
        
        //Check if it's missing an ID
        if (object.get_id() == null ) {
            object.set_id(String.valueOf(new ObjectId()));
        } else {
            throw new IllegalStateException("Cannot insert an object that already has an ID.");
        }
        
        //parsing to get implicit mapping...
        //Convenient but perhaps perfomance-costly
        final String objectJson = JSONMapper.getJson(object);
        LOG.warning("Created JSON for Mongo from Object...");
        LOG.warning(objectJson);
        collection.insertOne( Document.parse(objectJson));
        
        return object.get_id();
    }
    
    @Override
    public void update(final MongoPersistenceObject object) {
        MongoCollection collection = getCollection(object);
        
        //Check if it's missing an ID
        if (object.get_id() == null ) {
            throw new IllegalArgumentException();
        }
        
        //parsing to get implicit mapping...
        //Convenient but perhaps perfomance-costly
        final String objectJson = JSONMapper.getJson(object);
        LOG.warning("Created JSON for Mongo from Object...");
        LOG.warning(objectJson);
        UpdateResult result = collection.replaceOne( eq("_id" , object.get_id()) , Document.parse(objectJson) );
    }
    
    @Override
    public Object get(MongoPersistenceObject object) {
        MongoCollection collection = getCollection(object);
        Document doc = (Document) collection.find(eq("_id", object.get_id())).first();
        if (doc == null) {
            return null;
        }
        return (MongoPersistenceObject) JSONMapper.fromJson(doc.toJson(), object.getClass());
    } 

    @Override
    public void delete(MongoPersistenceObject object) {
        MongoCollection collection = getCollection(object);
        collection.deleteOne(eq("_id", object.get_id()));
    }
    
    
}
