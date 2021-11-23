package com.haulmont.testtask.repository;

import com.haulmont.testtask.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query("select p from Payment p where p.creditOffer.id = ?1")
    Page<Payment> findPaymentPage(UUID id, Pageable pageable);

    @Modifying
    @Query("delete from Payment p where p.creditOffer.id = ?1")
    void deleteByCreditOffer_Id(UUID id);

}