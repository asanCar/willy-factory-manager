package com.wonka.rrhh.oompamanager.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class JsonUtilTest {

    private JsonUtil sut;

    @Test
    public void marshallShouldReturnString() {
        //given
        final Map<String, String> map = getHashMapExample();

        //when
        final String outcome = JsonUtil.marshall(map);

        //then
        assertThat(outcome).isEqualTo(getJsonExample());
    }

    @Test
    public void unmarshallShouldReturnObject() {
        //given
        final String json = getJsonExample();

        //when
        final Map<String, String> outcome = JsonUtil.unmarshall(json, new TypeReference<Map<String, String>>() {
        });

        //then
        assertThat(outcome).isEqualTo(getHashMapExample());
    }

    @Test
    public void unmarshallShouldReturnNull() {
        //given
        final String json = getMalformedJson();

        //when
        final Map<String, String> outcome = JsonUtil.unmarshall(json, new TypeReference<Map<String, String>>() {
        });

        //then
        assertThat(outcome).isNull();
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