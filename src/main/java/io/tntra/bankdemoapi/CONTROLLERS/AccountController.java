package io.tntra.bankdemoapi.CONTROLLERS;


import io.tntra.bankdemoapi.Exceptions.InsufficientBalanceException;
import io.tntra.bankdemoapi.Exceptions.minimumbalanceException;
import io.tntra.bankdemoapi.SERVICES.AccountService;
import io.tntra.bankdemoapi.model.Account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;



@RestController
public class AccountController {


    AccountService AccountService;

    @RequestMapping(value = "/acc")
    public ResponseEntity<Object> getAccount() {
        return new ResponseEntity<>(AccountService.getAccount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/acc", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody Account account) throws minimumbalanceException {
        System.out.println(account);
        AccountService.createAccount(account);
        return new ResponseEntity<>(" Account is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/acc/details/{name}",method = RequestMethod.GET)
    public ResponseEntity<Object> detailsAccount(@PathVariable("name")String name){
           return new ResponseEntity<>(AccountService.detailsAccount(name),HttpStatus.OK);
    }

    @RequestMapping(value = "/acc/balance/{name}",method = RequestMethod.GET)

    public ResponseEntity<Object> Balance(@PathVariable("name")String name){

        return new ResponseEntity<>(name+" Account Balance is : "+AccountService.Balance(name),HttpStatus.OK);
    }



    @RequestMapping(value = "/acc/balance/{name}/{amount}",method = RequestMethod.GET)

    public ResponseEntity<Object> Deposit(@PathVariable("name")String name, @PathVariable BigDecimal amount){
        AccountService.Deposit(name,amount);
        return new ResponseEntity<>(name+" Deposit is successfully : ",HttpStatus.OK);
    }

    @RequestMapping(value = "/acc/balance/Withdraw/{name}/{amount}",method = RequestMethod.GET)

    public ResponseEntity<Object> Withdraw(@PathVariable("name")String name, @PathVariable BigDecimal amount) throws InsufficientBalanceException {
        AccountService.Withdraw(name,amount);
        return new ResponseEntity<>(name+" Withdraw is successfully : ",HttpStatus.OK);
    }


}
