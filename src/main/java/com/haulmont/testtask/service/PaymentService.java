package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.paging.Paged;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PaymentService extends  BaseService<Payment> {

    public void deleteByCreditOffer_Id(UUID id);

    public Paged<Payment> getPageByCreditOffer(int pageNumber, int size, UUID id);
}
