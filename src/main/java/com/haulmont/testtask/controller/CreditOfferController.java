package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.DataResult;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.service.ClientService;
import com.haulmont.testtask.service.CreditOfferService;
import com.haulmont.testtask.service.CreditService;
import com.haulmont.testtask.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CreditOfferController extends BaseController<CreditOffer> {
    public final CreditService creditService;
    public final ClientService clientService;
    public final PaymentService paymentService;
    public final CreditOfferService creditOfferService;

    public CreditOfferController(CreditOfferService creditOfferService, CreditService creditService,
                                 ClientService clientService, PaymentService paymentService) {
        super(creditOfferService);
        this.creditService = creditService;
        this.clientService = clientService;
        this.paymentService = paymentService;
        this.creditOfferService = creditOfferService;

    }

    @Override
    protected String getNamePath() {
        return "offer";
    }

    @Override
    @GetMapping("/offers")
    protected String getEntitiesPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                     @RequestParam(value = "size", required = false, defaultValue = "25") int size, Model model) {
        super.getEntitiesPage(pageNumber, size, model);
        model.addAttribute("credits", creditService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("entity", new CreditOffer());
        return "read/offers";
    }

    @Override
    @RequestMapping(value = "/offers/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    protected DataResult addEntity(@ModelAttribute("offer") @Valid CreditOffer entity, BindingResult result) {

        if (entity.getCredit().getCreditLimit() < entity.getCreditSum()) {
            String errMessage = "should be less than credit limit (" + entity.getCredit().getCreditLimit() + ")";
            FieldError fieldError = new FieldError("offer", "creditSum", errMessage);
            result.addError(fieldError);
        }
        if (!entity.getCredit().getBank().equals(entity.getClient().getBank())) {
            String errMessage = " bank should be equals client bank";
            FieldError fieldError = new FieldError("offer", "credit", errMessage);
            result.addError(fieldError);
        }
        UUID id = entity.getId();
        DataResult dataResult = super.addEntity(entity, result);
        if (dataResult.isSuccess) {

            if (id != null) {
                //if attributes that affected on payment list was changed
                paymentService.deleteByCreditOffer_Id(id);
                paymentService.saveAll(entity.getPayments());
            }
        }

        return dataResult;
    }

    @Override
    @RequestMapping(value = "/offers/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    protected String deleteEntity(@PathVariable String id) {
        super.deleteEntity(id);
        return "redirect:/offers";
    }

    @RequestMapping("offers/get")
    @ResponseBody
    protected Optional<CreditOffer> getEntity(String id) {
        return creditOfferService.findById(UUID.fromString(id));
    }

}