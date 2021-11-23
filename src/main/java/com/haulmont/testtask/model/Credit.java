package com.haulmont.testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "credit")
@Entity
public class Credit {
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false)
    @Id
    private UUID id;

    @NotNull(message = "The credit limit cannot be null")
    @Positive(message = "The credit limit should be > 0")
    @Column(name = "credit_limit", nullable = false)
    private Double creditLimit;

    @NotNull(message = "The percent cannot be null")
    @Max(message = "Max value of percent  = 100", value = 100)
    @Positive(message = "The percent should be > 0")
    @Column(name = "percent", nullable = false)
    private Double percent;

    @OneToMany(mappedBy = "credit", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<CreditOffer> creditOffers = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<CreditOffer> getCreditOffers() {
        return creditOffers;
    }

    public void setCreditOffers(List<CreditOffer> creditOffers) {
        this.creditOffers = creditOffers;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}