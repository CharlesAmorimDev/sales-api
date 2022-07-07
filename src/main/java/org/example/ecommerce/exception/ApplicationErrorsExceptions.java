package org.example.ecommerce.exception;

import java.util.Arrays;
import java.util.List;

public class ApplicationErrorsExceptions {

    private List<String> errors;

    public ApplicationErrorsExceptions(String messageErro){
        errors = Arrays.asList(messageErro);
    }

    public ApplicationErrorsExceptions(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
