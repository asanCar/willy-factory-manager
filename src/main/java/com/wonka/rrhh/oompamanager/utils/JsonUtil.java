package com.wonka.rrhh.oompamanager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static <T> String marshall(T object) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            //log
            return null;
        }
    }

    public static <T> T unmarshall(String json, TypeReference<T> type) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            //log
            return null;
        }
    }
}
