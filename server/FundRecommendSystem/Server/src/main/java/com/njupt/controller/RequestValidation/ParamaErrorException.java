package com.njupt.controller.RequestValidation;

public class ParamaErrorException extends RuntimeException {
    public ParamaErrorException() {
    }

    public ParamaErrorException(String message) {
        super(message);
    }

}
