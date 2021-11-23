package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.DataResult;
import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BankController extends BaseController<Bank> {
    public final BankService bankService;

    @Autowired
    public BankController(BankRepository repository, BankService bankService) {
        super(bankService);
        this.bankService = bankService;
    }

    @Override
    protected String getNamePath() {
        return "bank";
    }


    @Override
    @GetMapping("/banks")
    protected String getEntitiesPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                     @RequestParam(value = "size", required = false, defaultValue = "25") int size, Model model) {
        super.getEntitiesPage(pageNumber, size, model);
        model.addAttribute("entity", new Bank());
        return "read/banks";
    }

    @Override
    @RequestMapping(value = "/banks/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    protected DataResult addEntity(@Valid Bank entity, BindingResult result) {
        return super.addEntity(entity, result);
    }

    @Override
    @RequestMapping(value = "/banks/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    protected String deleteEntity(@PathVariable String id) {
        super.deleteEntity(id);
        return "redirect:/banks";
    }

    @RequestMapping("banks/get")
    @ResponseBody
    protected Optional<Bank> getEntity(String id) {
        return baseService.findById(UUID.fromString(id));
    }

}
