package io.tntra.bankdemoapi.Exceptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String message) {
        super(message);
}
}
