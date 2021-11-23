package com.haulmont.testtask.controller;

import com.haulmont.testtask.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {

        this.paymentService = paymentService;
    }


    @GetMapping("/payments/offerId/{id}")
    protected String getEntitiesPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                     @RequestParam(value = "size", required = false, defaultValue = "25") int size,
                                     @PathVariable("id") String id, Model model) {
        model.addAttribute("entities", paymentService.getPageByCreditOffer(pageNumber, size, UUID.fromString(id)));
        model.addAttribute("module", "payments/offerId/" + id);
        return "read/paymentSchedule";
    }
}
