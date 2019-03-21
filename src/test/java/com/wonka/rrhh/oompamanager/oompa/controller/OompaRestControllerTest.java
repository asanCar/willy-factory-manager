package com.wonka.rrhh.oompamanager.oompa.controller;

import com.wonka.rrhh.oompamanager.exceptions.ResourceNotFoundException;
import com.wonka.rrhh.oompamanager.oompa.dto.OompaDTO;
import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import com.wonka.rrhh.oompamanager.oompa.service.OompaService;
import com.wonka.rrhh.oompamanager.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.wonka.rrhh.oompamanager.config.constants.OompaExample.*;
import static com.wonka.rrhh.oompamanager.oompa.constants.OompaRestConstants.DEFAULT_URI;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OompaRestController.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class OompaRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OompaService service;

    @Test
    public void getAllOompaShouldReturnOompaList() throws Exception {

        final List<OompaDTO> oompaList = getExampleOompaDTOList();
        given(service.getAllOompa()).willReturn(oompaList);

        mvc.perform(get(DEFAULT_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(oompaList.size())))
                .andExpect(content().json(JsonUtil.INSTANCE.marshall(oompaList)));
    }

    @Test
    public void oompaDetailShouldBeReturned() throws Exception {

        final Oompa oompa = getExampleOompa();
        given(service.getOompaDetail(ANY_ID)).willReturn(oompa);

        mvc.perform(get(DEFAULT_URI + "/" + ANY_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.INSTANCE.marshall(oompa)));
    }

    @Test
    public void oompaDetailShouldReturn404Code() throws Exception {

        given(service.getOompaDetail(ANY_ID)).willThrow(new ResourceNotFoundException("Oompa", "id", String.valueOf(ANY_ID)));

        mvc.perform(get(DEFAULT_URI + "/" + ANY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addNewOompaShouldAddOompa() throws Exception {

        final Oompa oompa = getExampleOompa();
        final Oompa oompaCreated = getExampleOompa();
        oompaCreated.setId(ANY_ID);
        given(service.addOompa(oompa)).willReturn(oompaCreated);

        final String marshalledOompa = JsonUtil.INSTANCE.marshall(oompa);
        final String marshalledCreatedOompa = JsonUtil.INSTANCE.marshall(oompaCreated);

        mvc.perform(post(DEFAULT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalledOompa))
                .andExpect(status().isCreated())
                .andExpect(content().json(marshalledCreatedOompa));
    }

    @Test
    public void addNewOompaShouldReturn400Code() throws Exception {

        final Oompa oompa = new Oompa();
        final String marshalledOompa = JsonUtil.INSTANCE.marshall(oompa);

        mvc.perform(post(DEFAULT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalledOompa))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void oompaShouldBeEdited() throws Exception {

        final Oompa oompa = getExampleOompa();
        final Oompa oompaEdited = getExampleOompa();
        oompaEdited.setId(ANY_ID);
        given(service.updateOompa(ANY_ID, oompa)).willReturn(oompaEdited);

        final String marshalledOompa = JsonUtil.INSTANCE.marshall(oompa);
        final String marshalledUpdatedOompa = JsonUtil.INSTANCE.marshall(oompaEdited);

        mvc.perform(put(DEFAULT_URI + "/" + ANY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalledOompa))
                .andExpect(status().isOk())
                .andExpect(content().json(marshalledUpdatedOompa));
    }

    @Test
    public void editOompaShouldReturn404Code() throws Exception {

        final Oompa oompa = getExampleOompa();
        given(service.updateOompa(ANY_ID, oompa)).willThrow(new ResourceNotFoundException("Oompa", "id", String.valueOf(ANY_ID)));

        final String marshalledOompa = JsonUtil.INSTANCE.marshall(oompa);

        mvc.perform(put(DEFAULT_URI + "/" + ANY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalledOompa))
                .andExpect(status().isNotFound());
    }

    @Test
    public void editOompaShouldReturn400Code() throws Exception {

        final Oompa oompa = new Oompa();
        final String marshalledOompa = JsonUtil.INSTANCE.marshall(oompa);

        mvc.perform(put(DEFAULT_URI + "/" + ANY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalledOompa))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn500Code() throws Exception {

        given(service.getAllOompa()).willThrow(RuntimeException.class);

        mvc.perform(get(DEFAULT_URI ))
                .andExpect(status().isInternalServerError());
    }
}