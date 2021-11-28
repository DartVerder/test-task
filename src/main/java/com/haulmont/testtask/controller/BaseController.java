package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.DataResult;
import com.haulmont.testtask.service.BaseService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.UUID;


public abstract class BaseController<E> {

    protected final BaseService<E> baseService;

    protected abstract String getNamePath();

    public BaseController(BaseService<E> baseService) {

        this.baseService = baseService;

    }

    protected String getEntitiesPage(int pageNumber, int size, Model model) {

        model.addAttribute("entities", baseService.getPage(pageNumber, size));
        model.addAttribute("module", getNamePath() + "s");
        model.addAttribute("moduleAddPageName", "add" + firstUpperCase(getNamePath()));
        return "";
    }

    //create or update entity
    protected DataResult addEntity(@Valid E entity, BindingResult result) {
        DataResult dataResult = new DataResult();
        dataResult.setSuccess(true);

        if (result.hasErrors()) {
            dataResult.setErrors(result.getAllErrors());
            dataResult.setSuccess(false);
            return dataResult;
        }
        try {
            baseService.save(entity);
            dataResult.setNeedRedirect(true);
            dataResult.setRedirectUrl("/" + getNamePath() + "s");
        } catch (Exception e) {
            dataResult.addError(new ObjectError(getNamePath(), e.getLocalizedMessage()));
            dataResult.setSuccess(false);
        }
        return dataResult;
    }

    protected String deleteEntity(@PathVariable String id) {
        baseService.deleteById(UUID.fromString(id));
        return "";
    }

    protected String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
