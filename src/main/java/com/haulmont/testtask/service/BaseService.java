package com.haulmont.testtask.service;

import com.haulmont.testtask.paging.Paged;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<TEntity> {
    Paged<TEntity> getPage(int pageNumber, int size);

    void save(TEntity tEntity);

    void saveAll(List<TEntity> tEntities);

    public void deleteById(UUID id);

    public List<TEntity> findAll();

    public Optional<TEntity> findById(UUID id);
}
