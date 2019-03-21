package com.wonka.rrhh.oompamanager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public enum JsonUtil {

    INSTANCE;

    public <T> String marshall(final T object) throws JsonProcessingException {

        final ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }

    public <T> T unmarshall(final String json, final TypeReference<T> type) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, type);
    }
}
