package io.tntra.bankdemoapi.SERVICES;

import io.tntra.bankdemoapi.Exceptions.InsufficientBalanceException;
import io.tntra.bankdemoapi.Exceptions.minimumbalanceException;
import io.tntra.bankdemoapi.model.Account;

import java.math.BigDecimal;
import java.util.Map;

public interface BankAccountServices {
    public void createAccount(Account account) throws minimumbalanceException;

    public Account detailsAccount(String name);



    public BigDecimal Balance(String name);


    BigDecimal Deposit(String name, BigDecimal amount);

    public BigDecimal Withdraw(String name, BigDecimal amount) throws InsufficientBalanceException;
}
