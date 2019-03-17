package com.wonka.rrhh.oompamanager.utils.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface BasicRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S var1);

    boolean existsById(ID var1);

    Optional<T> findById(ID var1);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> var1);

    void deleteById(ID var1);

//    void delete(T var1);
}
