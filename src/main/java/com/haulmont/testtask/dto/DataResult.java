package com.haulmont.testtask.dto;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class DataResult {

    public boolean isSuccess;

    public boolean isNeedRedirect;

    public String redirectUrl;

    public List<ObjectError> errors = new ArrayList<>();

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isNeedRedirect() {
        return isNeedRedirect;
    }

    public void setNeedRedirect(boolean needRedirect) {
        isNeedRedirect = needRedirect;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }

    public void addError (ObjectError error) {
        this.errors.add(error);
    }





}
