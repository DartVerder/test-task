package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.paging.Paged;
import com.haulmont.testtask.paging.Paging;
import com.haulmont.testtask.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseServiceImpl<E> implements BaseService<E> {

    protected final JpaRepository<E,UUID> repository;

    public BaseServiceImpl(JpaRepository<E,UUID> repository) {
        this.repository = repository;
    }

    /*Вернуть страницу из списка*/
    public Paged<E> getPage(int pageNumber, int size)
    {
        PageRequest request = PageRequest.of(pageNumber-1, size);
        Page<E> clientPage = repository.findAll(request);
        return new Paged<>(clientPage, Paging.of(clientPage.getTotalPages(),pageNumber,size));
    }

    public void save(E entity){
        repository.save(entity);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<E> findById(UUID id) {
        return repository.findById(id);
    }

    public void saveAll(List<E> entities){
        repository.saveAll(entities);
    }

}
