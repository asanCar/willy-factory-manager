package com.wonka.rrhh.oompamanager.oompa.controller;

import com.wonka.rrhh.oompamanager.oompa.dto.OompaDTO;
import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import com.wonka.rrhh.oompamanager.oompa.service.OompaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.wonka.rrhh.oompamanager.oompa.constants.OompaRestConstants.DEFAULT_URI;

@RestController
@RequestMapping(DEFAULT_URI)
@Api("Oompa Loompa's REST Controller")
public class OompaRestController {

    private static final Logger log = LoggerFactory.getLogger(OompaRestController.class);

    private final OompaService oompaService;

    @Autowired
    public OompaRestController(OompaService oompaService) {

        this.oompaService = oompaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all Oompa Loompa")
    public ResponseEntity<List<OompaDTO>> getAllOompa() {

        final List<OompaDTO> allOompas = oompaService.getAllOompa();

        return new ResponseEntity<>(allOompas, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new Oompa Loompa")
    public ResponseEntity<Oompa> addNewOompa(@Valid @RequestBody Oompa oompa) {

        final Oompa createdOompa = oompaService.addOompa(oompa);

        return new ResponseEntity<>(createdOompa, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get full detail of an Oompa Loompa")
    public ResponseEntity<Oompa> getOompaDetail(
            @ApiParam(value = "Oompa Loompa's Id", required = true) @PathVariable Long id) {

        final Oompa oompaDetail = oompaService.getOompaDetail(id);

        return new ResponseEntity<>(oompaDetail, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit Oompa Loompa")
    public ResponseEntity<Oompa> editOompa(
            @ApiParam(value = "Oompa Loompa's Id", required = true) @PathVariable Long id,
            @Valid @RequestBody Oompa oompa) {

        final Oompa editedOompa = oompaService.updateOompa(id, oompa);

        return new ResponseEntity<>(editedOompa, HttpStatus.OK);
    }
}
