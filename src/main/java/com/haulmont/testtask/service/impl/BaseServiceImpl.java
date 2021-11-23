package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.paging.Paged;
import com.haulmont.testtask.paging.Paging;
import com.haulmont.testtask.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseServiceImpl<TEntity> implements BaseService<TEntity> {

    protected final JpaRepository repository;

    public BaseServiceImpl(JpaRepository repository) {
        this.repository = repository;
    }

    /*Вернуть страницу из списка*/
    public Paged<TEntity> getPage(int pageNumber, int size)
    {
        PageRequest request = PageRequest.of(pageNumber-1, size);
        Page<TEntity> clientPage = repository.findAll(request);
        return new Paged(clientPage, Paging.of(clientPage.getTotalPages(),pageNumber,size));
    }

    public void save(TEntity tEntity){
        repository.save(tEntity);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }

    @Override
    public List<TEntity> findAll() {
        return repository.findAll();
    }

  /*  @Override
    public List<TEntity> findAllSorted() {
        Sort sort = new Sort();
        return repository.findAll(new Sort(Sort.Direction.DESC,));
    }*/

    @Override
    public Optional<TEntity> findById(UUID id) {
        return repository.findById(id);
    }

    public void saveAll(List<TEntity> tEntities){
        repository.saveAll(tEntities);
    }

}
