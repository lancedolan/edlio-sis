/*
 * Edlio Licensing Info Here.
 */
package com.edlio.sis.dao.impl.mongo;

import com.edlio.sis.model.School;
import com.edlio.sis.model.SisClass;
import com.edlio.sis.model.Student;
import com.edlio.sis.model.Teacher;
import com.edlio.sis.model.User;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 *
 * @author lance
 */
final class ClassToCollectionMapper {
     
    /**
     * Map Key: canonical name of any known class in business model
     * Map Entry: the mongoDB collection that should be used for that class
     */
    final private Map<String , String> collectionMapping = 
            ImmutableMap.of(
                SisClass.class.getCanonicalName() ,  "class",
                School.class.getCanonicalName() ,  "school",
                Student.class.getCanonicalName() ,  "student",
                User.class.getCanonicalName() ,  "user",
                Teacher.class.getCanonicalName() ,  "teacher"
            );
     
     
    String getMongoCollectionName( final Object object) {
        final String returnName = collectionMapping.get(object.getClass().getCanonicalName());
        if (returnName!=null) {
            return returnName;
        }
        throw new IllegalArgumentException();
    }
     
}
