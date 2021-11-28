package com.haulmont.testtask.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "payment_schedule")
@Entity
public class Payment {
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_sum")
    private Double paymentSum;

    @Column(name = "payment_to_credit")
    private Double paymentToCredit;

    @Column(name = "payment_to_percents")
    private Double paymentToPercents;

    @ManyToOne
    @JoinColumn(name = "credit_offer_id")
    private CreditOffer creditOffer;

    public CreditOffer getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getPaymentToPercents() {
        return paymentToPercents;
    }

    public void setPaymentToPercents(Double paymentToPercents) {
        this.paymentToPercents = paymentToPercents;
    }

    public Double getPaymentToCredit() {
        return paymentToCredit;
    }

    public void setPaymentToCredit(Double paymentToCredit) {
        this.paymentToCredit = paymentToCredit;
    }

    public Double getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(Double paymentSum) {
        this.paymentSum = paymentSum;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

}