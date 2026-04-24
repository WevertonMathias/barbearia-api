package com.barbearia.api.infra;

public class VerificarSeEmailExisteException extends RuntimeException{

    private static final String EMAIL_EXISTENTE = "O email ja existe";

    public VerificarSeEmailExisteException() {
        super(EMAIL_EXISTENTE);
    }
}
