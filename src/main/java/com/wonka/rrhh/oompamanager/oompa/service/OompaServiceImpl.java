package com.wonka.rrhh.oompamanager.oompa.service;

import com.wonka.rrhh.oompamanager.exceptions.ResourceNotFoundException;
import com.wonka.rrhh.oompamanager.oompa.dto.OompaDTO;
import com.wonka.rrhh.oompamanager.oompa.entity.Oompa;
import com.wonka.rrhh.oompamanager.oompa.repository.OompaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OompaServiceImpl implements OompaService {

    private final OompaRepository repository;

    @Autowired
    public OompaServiceImpl(OompaRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<OompaDTO> getAllOompa() {

        final List<OompaDTO> result = new ArrayList<>();
        for (Oompa oompa : repository.findAll()) {
            result.add(new OompaDTO(oompa));
        }

        return result;
    }

    @Override
    public Oompa getOompaDetail(final Long id) {

        final Optional<Oompa> oompa = repository.findById(id);
        return oompa
                .orElseThrow(() -> getResourceNotFoundException(String.valueOf(id)));
    }

    @Override
    public Oompa addOompa(final Oompa oompa) {

        return repository.save(oompa);
    }

    @Override
    public Oompa updateOompa(final Long id, final Oompa oompa) {

        if (repository.existsById(id)) {
            oompa.setId(id);
            return repository.save(oompa);
        } else {
            throw getResourceNotFoundException(String.valueOf(id));
        }
    }

    private ResourceNotFoundException getResourceNotFoundException(final String id) {

        return new ResourceNotFoundException("Oompa Loompa", "id", id);
    }
}
