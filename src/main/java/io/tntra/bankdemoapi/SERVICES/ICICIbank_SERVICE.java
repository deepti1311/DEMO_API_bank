package io.tntra.bankdemoapi.SERVICES;

import io.tntra.bankdemoapi.Exceptions.InsufficientBalanceException;
import io.tntra.bankdemoapi.Exceptions.minimumbalanceException;
import io.tntra.bankdemoapi.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class ICICIbank_SERVICE extends AccountService implements BankAccountServices  {

    @Override
    public void createAccount(Account account) throws minimumbalanceException {
        account.setMin_balance(BigDecimal.valueOf(1000));
        account.setOverDraft(BigDecimal.valueOf(0));

        if(account.getBalance().compareTo(account.getMin_balance())>=0){
            super.createAccount(account);
        }else {
            throw new minimumbalanceException("minimum balance of rs"+account.min_balance+" is required");
        }

    }
    @Override
    public Collection<Account> getAccount() {
        return super.getAccount();
    }
    @Override
    public Account detailsAccount(String name) {
        return super.detailsAccount(name);
    }


    @Override
    public BigDecimal Balance(String name){
        return  super.Balance(name);

    }


    @Override
    public BigDecimal Deposit(String name, BigDecimal amount) {

        return super.Deposit(name, amount);

    }

    @Override
    public BigDecimal Withdraw(String name, BigDecimal amount) throws InsufficientBalanceException {
        return super.Withdraw(name, amount);
    }

    @Override
    public boolean checkBalance(String name,BigDecimal amount){
        Account account = detailsAccount(name);
        account.setOverDraft(account.getBalance().multiply(BigDecimal.valueOf(0.2)));
        return super.checkBalance(name, amount);
    }

}
