package com.haulmont.testtask.service;

import com.haulmont.testtask.paging.Paged;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<E> {
    Paged<E> getPage(int pageNumber, int size);

    void save(E entity);

    void saveAll(List<E> entities);

    void deleteById(UUID id);

    List<E> findAll();

    Optional<E> findById(UUID id);
}
