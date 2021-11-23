package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.repository.ClientRepository;
import com.haulmont.testtask.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends BaseServiceImpl<Client> implements ClientService {

    public ClientServiceImpl(ClientRepository repository) {
        super(repository);
    }
}
