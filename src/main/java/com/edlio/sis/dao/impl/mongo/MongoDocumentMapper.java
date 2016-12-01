/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo;

import com.edlio.sis.model.SisClass;
import org.bson.Document;

/**
 * Used to produce Mongo Documents from Business Model POJOSs.
 * In a production application, I imagine using some annotations
 * library to get this done for me.
 * @author lance
 */
class MongoDocumentMapper {
    
    Document getDocument(final SisClass sisClass) {
        Document doc = new Document();
        if (sisClass.get_id() != null) {
            doc.append("_id", sisClass.get_id());
        }
        doc.append("title", sisClass.getTitle());
        doc.append("period", sisClass.getPeriod());
        return doc;
    }
}
