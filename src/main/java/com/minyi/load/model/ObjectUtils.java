package com.minyi.load.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ObjectUtils {

    private static final ObjectMapper mapper = JsonMapper.builder()
            .build();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "Can't convert to JSON String.";
        }
    }


}
