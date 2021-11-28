package com.haulmont.testtask.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "credit_offer")
@Entity
public class CreditOffer {
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;


    @Positive
    @Column(name = "credit_sum")
    private Double creditSum;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Payment> payments;

    @Positive
    @Column(name = "months_count", nullable = false)
    private Integer monthsCount;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @NotNull
    @Enumerated
    @Column(name = "payment_schedule_type", nullable = false)
    private PaymentScheduleType paymentScheduleType;

    public void setPayments(List<Payment> payments) {
        if (monthsCount != null)
            this.payments = new ArrayList<>(monthsCount);
    }

    public PaymentScheduleType getPaymentScheduleType() {
        return paymentScheduleType;
    }

    public void setPaymentScheduleType(PaymentScheduleType paymentScheduleType) {
        this.paymentScheduleType = paymentScheduleType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CreditOffer(Client client, Credit credit, Double creditSum,
                       Integer monthsCount, Date startDate) {
        this.client = client;
        this.credit = credit;
        this.creditSum = creditSum;
        this.monthsCount = monthsCount;
        this.startDate = startDate;
        this.payments = new ArrayList<>(monthsCount);
    }

    public CreditOffer() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getMonthsCount() {
        return monthsCount;
    }

    public void setMonthsCount(Integer monthsCount) {
        this.monthsCount = monthsCount;
    }

    public List<Payment> getPayments() {
        if (payments == null || payments.size() == 0) {
            prePersist();
        }
        return payments;
    }

    @Transient
    public Double getPercentsSum() {
        Double sum = 0.0;
        for (Payment payment : payments) {
            sum += payment.getPaymentToPercents();
        }
        return roundDouble(sum);
    }

    public Double getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(Double creditSum) {
        this.creditSum = creditSum;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @PrePersist
    public void prePersist() {
        LocalDate nextDate;
        if (startDate instanceof java.sql.Date) {
            nextDate = ((java.sql.Date) startDate).toLocalDate();
        } else {
            nextDate = startDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        Double balance = creditSum;
        Double percentPerMonth = this.credit.getPercent() / 1200;
        Double pay, payToPercent, payToCredit;
        if (monthsCount != null) {
            this.payments = new ArrayList<>(monthsCount);
            if (this.paymentScheduleType.equals(PaymentScheduleType.ANNUAL)) {
                pay = roundDouble(balance * percentPerMonth / (1 - Math.pow(1 + percentPerMonth, -(monthsCount))));

                for (int i = 0; i < monthsCount; i++) {
                    //Процент по кредиту = Остаток задолженности*(ставка %/12)
                    payToPercent = roundDouble(balance * percentPerMonth);
                    payToCredit = roundDouble(pay - payToPercent);
                    nextDate = nextDate.plusMonths(1);
                    balance = createPayment(balance, nextDate, pay, payToPercent, payToCredit);
                }
            } else if (this.paymentScheduleType.equals(PaymentScheduleType.DIFFERENTIATED)) {
                payToCredit = roundDouble(creditSum / monthsCount);
                for (int i = 0; i < monthsCount; i++) {
                    //Процент по кредиту = Остаток задолженности*(ставка %/12)
                    payToPercent = roundDouble(balance * percentPerMonth);
                    nextDate = nextDate.plusMonths(1);
                    pay = roundDouble(payToCredit + payToPercent);

                    balance = createPayment(balance, nextDate, pay, payToPercent, payToCredit);
                }
            }
        }
    }

    @NotNull
    private Double createPayment(Double balance, LocalDate nextDate, double pay, double payToPercent, double payToCredit) {
        Payment payment = new Payment();
        payment.setPaymentDate(nextDate);
        payment.setPaymentSum(pay);
        payment.setPaymentToPercents(payToPercent);
        payment.setPaymentToCredit(payToCredit);
        payment.setCreditOffer(this);

        payments.add(payment);

        balance -= payToCredit;
        return roundDouble(balance);
    }

    private Double roundDouble(Double number) {
        return ((double) Math.round(number * 100.0)) / 100.0;
    }
}