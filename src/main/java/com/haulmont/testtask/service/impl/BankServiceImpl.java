package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.service.BankService;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends BaseServiceImpl<Bank> implements BankService {

    public BankServiceImpl(BankRepository repository) {
        super(repository);
    }
}
