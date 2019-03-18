package com.wonka.rrhh.oompamanager.oompa.controller;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.wonka.rrhh.oompamanager.config.constants.OompaExample.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OompaRestController.class)
public class OompaRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OompaService service;

    @Test
    public void getAllOompaShouldReturnOompaList() throws Exception {
        final List<OompaDTO> oompaList = getExampleOompaDTOList();
        given(service.getAllOompa()).willReturn(oompaList);

        mvc.perform(get("/api/v1/oompas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(oompaList.size())))
                .andExpect(content().json(JsonUtil.marshall(oompaList)));
    }

    @Test
    public void oompaDetailShouldBeReturned() throws Exception {
        final Oompa oompa = getExampleOompa();
        given(service.getOompaDetail(ANY_ID)).willReturn(oompa);

        mvc.perform(get("/api/v1/oompas/" + ANY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.marshall(oompa)));
    }
}