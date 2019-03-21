package com.wonka.rrhh.oompamanager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class JsonUtilTest {

    private JsonUtil sut = JsonUtil.INSTANCE;

    @Test
    public void marshallShouldReturnString() throws JsonProcessingException {
        //given
        final Map<String, String> map = getHashMapExample();

        //when
        final String outcome = sut.marshall(map);

        //then
        assertThat(outcome).isEqualTo(getJsonExample());
    }

    @Test(expected = JsonProcessingException.class)
    public void marshallShouldThrowJsonProcessingException() throws JsonProcessingException {
        //given
        final Map<String, String> map = new HashMap<>();
        map.put(null, null);

        //when
        sut.marshall(map);
    }

    @Test
    public void unmarshallShouldReturnObject() throws IOException {
        //given
        final String json = getJsonExample();

        //when
        final Map<String, String> outcome = sut.unmarshall(json, new TypeReference<Map<String, String>>() {
        });

        //then
        assertThat(outcome).isEqualTo(getHashMapExample());
    }

    @Test(expected = IOException.class)
    public void unmarshallShouldThrowIOException() throws IOException {
        //given
        final String json = getMalformedJson();

        //when
        sut.unmarshall(json, new TypeReference<Map<String, String>>() {
        });
    }

    //Test harness

    private String getJsonExample() {

        return "{\"key1\":\"value1\",\"key2\":\"value2\"}";
    }

    private String getMalformedJson() {

        return "{\"key1\",:\"value1\",\"key2\":\"value2\"}";
    }

    private Map<String, String> getHashMapExample() {

        final Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        return map;
    }
}