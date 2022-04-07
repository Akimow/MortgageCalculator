package org.bank.service;

public class MortgageException extends RuntimeException{
    public MortgageException(String case_not_handled) {
        super("Case not handled");
    }
}
