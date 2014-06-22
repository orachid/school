package fr.wati.school.web.rebirth.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.util.ISO8601DateFormat;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);            
        setDateFormat(new ISO8601DateFormat());
    }
}
