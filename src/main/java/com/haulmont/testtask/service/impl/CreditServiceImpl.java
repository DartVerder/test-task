package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.repository.CreditRepository;
import com.haulmont.testtask.service.CreditService;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl extends BaseServiceImpl<Credit> implements CreditService {

    public CreditServiceImpl(CreditRepository repository) {
        super(repository);
    }
}
