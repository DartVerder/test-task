package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.DataResult;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CreditController extends BaseController<Credit> {

    private final BankService bankService;

    @Autowired
    public CreditController(CreditService creditService, BankService bankService) {
        super(creditService);
        this.bankService = bankService;
    }

    @Override
    protected String getNamePath() {
        return "credit";
    }

    @Override
    @GetMapping("/credits")
    protected String getEntitiesPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                     @RequestParam(value = "size", required = false, defaultValue = "25") int size, Model model) {
        super.getEntitiesPage(pageNumber, size, model);
        model.addAttribute("banks",bankService.findAll());
        model.addAttribute("entity", new Credit());

        return "read/credits";
    }

    @Override
    @RequestMapping(value = "/credits/add", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    protected DataResult addEntity(@Valid Credit entity, BindingResult result) {

       return super.addEntity(entity, result);
    }

    @RequestMapping("/credits/get")
    @ResponseBody
    protected Optional<Credit> getEntity(String id) {
        return baseService.findById(UUID.fromString(id));
    }

    @Override
    @RequestMapping(value = "/credits/delete/{id}", method = {RequestMethod.DELETE,RequestMethod.GET})
    protected String deleteEntity(@PathVariable String id) {
        super.deleteEntity(id);
        return "redirect:/credits";
    }
}