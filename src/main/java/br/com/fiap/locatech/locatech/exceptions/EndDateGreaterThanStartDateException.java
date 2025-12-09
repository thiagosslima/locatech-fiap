package br.com.fiap.locatech.locatech.exceptions;

public class EndDateGreaterThanStartDateException extends RuntimeException {

    public EndDateGreaterThanStartDateException(String message) {
        super(message);
    }
}
