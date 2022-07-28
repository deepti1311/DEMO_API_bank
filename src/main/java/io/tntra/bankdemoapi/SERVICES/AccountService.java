package io.tntra.bankdemoapi.SERVICES;

import io.tntra.bankdemoapi.Enum.pkg.AccountType;
import io.tntra.bankdemoapi.Exceptions.InsufficientBalanceException;
import io.tntra.bankdemoapi.Exceptions.minimumbalanceException;
import io.tntra.bankdemoapi.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;


@Component
public class AccountService implements BankAccountServices {
    AccountType account_type;

    private static final Map<String, Account> AccountRepo = new HashMap<>();




    @Override
    public void createAccount(Account account) throws minimumbalanceException {

        AccountRepo.put(account.getName(), account);
    }

    public Collection<Account> getAccount() {
        return AccountRepo.values();
    }
    @Override
    public Account detailsAccount(String name){

      return  AccountRepo.get(name);

    }
    @Override
    public BigDecimal Balance(String name){
        return  AccountRepo.get(name).getBalance();

    }
    @Override
    public BigDecimal Deposit(String name, BigDecimal amount){

        BigDecimal bal=AccountRepo.get(name).getBalance();
         BigDecimal new_bal=bal.add(amount);
         AccountRepo.get(name).setBalance(new_bal);
        return  new_bal;
    }
    @Override
    public BigDecimal Withdraw(String name, BigDecimal amount) throws InsufficientBalanceException {


        try{
            if(checkBalance(name, amount)){
                BigDecimal bal=AccountRepo.get(name).getBalance();
                BigDecimal new_bal=bal.subtract(amount);
                AccountRepo.get(name).setBalance(new_bal);
                return  new_bal;

            }
            else throw new InsufficientBalanceException("Insufficient Balance FOR TRANSACTIONS!!");
        } catch (InsufficientBalanceException e) {
            e.getMessage();
            throw new InsufficientBalanceException("Insufficient Balance FOR TRANSACTIONS!!");
        }

    }

    public boolean checkBalance(String name,BigDecimal amount){

        if(this.account_type== AccountType.CURRENT){
            BigDecimal bal = AccountRepo.get(name).getBalance();
            BigDecimal temp = AccountRepo.get(name).getOverDraft().add(bal);
            return temp.compareTo(amount)>=0;
        }
        return AccountRepo.get(name).getBalance().compareTo(amount)>=0;
    }



}
