package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.paging.Paged;
import com.haulmont.testtask.paging.Paging;
import com.haulmont.testtask.repository.CreditOfferRepository;
import com.haulmont.testtask.service.CreditOfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CreditOfferServiceImpl extends BaseServiceImpl<CreditOffer> implements CreditOfferService {

    private final CreditOfferRepository creditOfferRepository;

    public Paged<CreditOffer> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.Direction.DESC, "startDate");
        Page<CreditOffer> clientPage = creditOfferRepository.findAll(request);
        return new Paged<>(clientPage, Paging.of(clientPage.getTotalPages(), pageNumber, size));
    }

    public CreditOfferServiceImpl(CreditOfferRepository repository) {
        super(repository);
        this.creditOfferRepository = repository;
    }
}
