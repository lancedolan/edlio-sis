package com.edlio.sis.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JSONMapper {
	private static final JsonFactory FACTORY = new JsonFactory().disable(Feature.AUTO_CLOSE_TARGET);
	private static final ObjectMapper MAPPER = new ObjectMapper();
        private static final Logger LOG = Logger.getLogger(JSONMapper.class.getName());
	
	public static final String getJson(Object object, Class<?> view){
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			JsonGenerator generator;
			generator = FACTORY.createJsonGenerator(baos);
			MAPPER.getSerializationConfig().set(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
                        if (view!=null) {
                            MAPPER.getSerializationConfig().setSerializationView(view);
                            MAPPER.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);
                        } else {
                            MAPPER.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, true);
                        }
			MAPPER.writeValue(generator, object);
			return new String(baos.toByteArray());
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}
		return null;
	}
        
        public static final String getJson(Object object) {
            return getJson(object, null);
        }
}
