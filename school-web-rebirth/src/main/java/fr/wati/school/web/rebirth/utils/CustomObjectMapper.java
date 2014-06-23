package fr.wati.school.web.rebirth.utils;

import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.util.ISO8601DateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
    	configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);          
        setDateFormat(new ISO8601DateFormat());
    }
}
