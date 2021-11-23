package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.DataResult;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ClientController extends BaseController<Client> {

    private final BankService bankService;

    @Autowired
    public ClientController(ClientService clientService, BankService bankService) {
        super(clientService);
        this.bankService = bankService;
    }

    @Override
    protected String getNamePath() {
        return "client";
    }

    @Override
    @GetMapping("/clients")
    protected String getEntitiesPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                     @RequestParam(value = "size", required = false, defaultValue = "25") int size, Model model) {
        super.getEntitiesPage(pageNumber, size, model);
        model.addAttribute("entity", new Client());
        model.addAttribute("banks", bankService.findAll());
        return "read/clients";
    }

    @Override
    @RequestMapping(value = "/clients/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    protected DataResult addEntity(@Valid Client entity, BindingResult result) {
        return super.addEntity(entity, result);
    }

    @RequestMapping("/clients/get")
    @ResponseBody
    protected Optional<Client> getEntity(String id) {
        return baseService.findById(UUID.fromString(id));
    }

    @Override
    @RequestMapping(value = "/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    protected String deleteEntity(@PathVariable String id) {
        super.deleteEntity(id);
        return "redirect:/clients";
    }
}