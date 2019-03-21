package com.wonka.rrhh.oompamanager.oompa.service;

import com.wonka.rrhh.oompamanager.oompa.dto.OompaDTO;
import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;

import java.util.List;

public interface OompaService {

    List<OompaDTO> getAllOompa();

    Oompa getOompaDetail(Long id);

    Oompa addOompa(Oompa oompa);

    Oompa updateOompa(Long id, Oompa oompa);
}
