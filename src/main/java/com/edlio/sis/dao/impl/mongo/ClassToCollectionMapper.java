/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo;

import com.edlio.sis.model.SisClass;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 *
 * @author lance
 */
final class ClassToCollectionMapper {
     
    
    final private Map<String , String> collectionMapping = 
            ImmutableMap.of(SisClass.class.getCanonicalName() ,  "class");
     
     
    String getMongoCollectionName( final Object object) {
        final String returnName = collectionMapping.get(object.getClass().getCanonicalName());
        if (returnName!=null) {
            return returnName;
        }
        throw new IllegalArgumentException();
    }
     
}
