package io.tntra.bankdemoapi.SERVICES;

import io.tntra.bankdemoapi.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;


@Service
public class AccountService {

    private static Map<String, Account> AccountRepo = new HashMap<>();


    public void createAccount(Account account) {
        AccountRepo.put(account.getId(), account);
        AccountRepo.put(account.getName(), account);
    }

    public Collection<Account> getAccount() {
        return AccountRepo.values();
    }

    public Account detailsAccount(String name){
      return  AccountRepo.get(name);

    }
    public BigDecimal Balance(String name){
        return  AccountRepo.get(name).getBalance();

    }


}
