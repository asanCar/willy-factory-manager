package com.wonka.rrhh.oompamanager.oompa.controller;

import com.wonka.rrhh.oompamanager.oompa.dto.OompaDTO;
import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import com.wonka.rrhh.oompamanager.oompa.service.OompaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wonka.rrhh.oompamanager.config.constants.OompaRestConstants.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/oompas")
@Api("Oompa Loompa's REST Controller")
public class OompaRestController {

    private final OompaService oompaService;

    @Autowired
    public OompaRestController(OompaService oompaService) {
        this.oompaService = oompaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all Oompa Loompa")
    public List<OompaDTO> getAllOompa() {

        return oompaService.getAllOompa();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get the full detail of an Oompa Loompa")
    public Oompa getOompaDetail(@ApiParam(value = "Oompa Loompa's Id", required = true) @PathVariable Long id) {
        return oompaService.getOompaDetail(id);
    }
}
