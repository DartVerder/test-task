package com.haulmont.testtask.repository;

import com.haulmont.testtask.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {


}