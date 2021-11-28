package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.paging.Paged;
import java.util.UUID;

public interface PaymentService extends  BaseService<Payment> {

    void deleteByCreditOffer_Id(UUID id);

    Paged<Payment> getPageByCreditOffer(int pageNumber, int size, UUID id);
}
