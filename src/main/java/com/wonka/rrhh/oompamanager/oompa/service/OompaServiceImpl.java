package com.wonka.rrhh.oompamanager.oompa.service;

import com.wonka.rrhh.oompamanager.eceptions.ResourceNotFoundException;
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
    public Oompa getOompaDetail(long id) {
        final Optional<Oompa> oompa = repository.findById(id);
        return oompa
                .orElseThrow(() -> new ResourceNotFoundException("Oompa Loompa", "id", String.format("%d", id)));
    }

    @Override
    public Oompa addOompa(Oompa oompa) {
        return repository.save(oompa);
    }

    @Override
    public Oompa updateOompa(Long id, Oompa oompa) {
        if (repository.existsById(id)) {
            return addOompa(oompa);
        } else {
            throw new ResourceNotFoundException("Oompa Loompa", "id", String.format("%d", id));
        }
    }

    @Override
    public void deleteOompa(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Oompa Loompa", "id", String.format("%d", id));
        }
    }
}
