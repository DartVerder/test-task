package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.paging.Paged;
import com.haulmont.testtask.paging.Paging;
import com.haulmont.testtask.repository.CreditOfferRepository;
import com.haulmont.testtask.repository.PaymentRepository;
import com.haulmont.testtask.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl extends BaseServiceImpl<Payment> implements PaymentService {
    private final CreditOfferRepository creditOfferRepository;

    public PaymentServiceImpl(PaymentRepository repository, CreditOfferRepository creditOfferRepository) {
        super(repository);
        this.creditOfferRepository = creditOfferRepository;
    }

    @Transactional
    public void deleteByCreditOffer_Id(UUID id) {
        ((PaymentRepository) repository).deleteByCreditOffer_Id(id);
    }

    public Paged<Payment> getPageByCreditOffer(int pageNumber, int size, UUID id) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Payment> paymentsPage = ((PaymentRepository) repository).findPaymentPage(id, request);
        if(paymentsPage.getTotalElements()==0){
            List<Payment> payments = creditOfferRepository.findById(id).get().getPayments();
            repository.saveAll(payments);
            paymentsPage = ((PaymentRepository) repository).findPaymentPage(id, request);
            if(paymentsPage.getTotalElements()==0)
                System.out.println("error");
        }
        return new Paged(paymentsPage, Paging.of(paymentsPage.getTotalPages(), pageNumber, size));
    }

}
