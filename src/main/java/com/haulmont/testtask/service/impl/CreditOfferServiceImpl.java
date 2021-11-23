package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.model.PaymentScheduleType;
import com.haulmont.testtask.paging.Paged;
import com.haulmont.testtask.paging.Paging;
import com.haulmont.testtask.repository.CreditOfferRepository;
import com.haulmont.testtask.service.CreditOfferService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CreditOfferServiceImpl extends BaseServiceImpl<CreditOffer> implements CreditOfferService {

    public Paged<CreditOffer> getPage(int pageNumber, int size)
    {
        PageRequest request = PageRequest.of(pageNumber-1, size, Sort.Direction.DESC,"startDate");
        Page<CreditOffer> clientPage = repository.findAll(request);
        return new Paged(clientPage, Paging.of(clientPage.getTotalPages(),pageNumber,size));
    }

   public CreditOfferServiceImpl(CreditOfferRepository repository) {
        super(repository);
    }
 /*
    public void prePersist(CreditOffer offer) {
        LocalDate nextDate = offer.getStartDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Double balance = offer.getCreditSum();
        Double percentPerMonth = offer.getCredit().getPercent() / 1200;
        Double pay,payToPercent,payToCredit;
        Integer monthsCount = offer.getMonthsCount();
        if (monthsCount != null) {
            offer.setPayments(new ArrayList<>(monthsCount));
            if (offer.getPaymentScheduleType().equals(PaymentScheduleType.ANNUAL)) {
                pay = roundDouble(balance * percentPerMonth / (1 - Math.pow(1 + percentPerMonth, -(monthsCount))));

                for (int i = 0; i < monthsCount; i++) {
                    //Процент по кредиту = Остаток задолженности*(ставка %/12)
                    payToPercent = roundDouble(balance * percentPerMonth);
                    payToCredit = roundDouble(pay - payToPercent);
                    nextDate = nextDate.plusMonths(1);
                    balance = createPayment(balance, nextDate, pay, payToPercent, payToCredit);
                }
            } else if (offer.getPaymentScheduleType().equals(PaymentScheduleType.DIFFERENTIATED)) {
                payToCredit = roundDouble(balance / monthsCount);
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

    private Double roundDouble(Double number){
        return ((double) Math.round(number * 100.0)) / 100.0;
    }
    */
}
