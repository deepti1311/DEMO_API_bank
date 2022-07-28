package io.tntra.bankdemoapi.CONTROLLERS;

import io.tntra.bankdemoapi.Exceptions.InsufficientBalanceException;
import io.tntra.bankdemoapi.Exceptions.minimumbalanceException;
import io.tntra.bankdemoapi.SERVICES.HDFCbank_SERVICE;
import io.tntra.bankdemoapi.SERVICES.ICICIbank_SERVICE;
import io.tntra.bankdemoapi.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@RestController
public class ICICI_CONTROLLER {

    final
    ICICIbank_SERVICE ICICIbank;

    public ICICI_CONTROLLER(ICICIbank_SERVICE ICICIbank) {
        this.ICICIbank = ICICIbank;
    }

    @RequestMapping(value = "/acc/icici")
    public ResponseEntity<Object> getAccount() {
        return new ResponseEntity<>(ICICIbank.getAccount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/acc/icici", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody Account account) throws minimumbalanceException {
        try{  System.out.println(account);
            ICICIbank.createAccount(account);
            return new ResponseEntity<>(" Account is created successfully", HttpStatus.OK);
        }catch (minimumbalanceException e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<>("Minimum balance for HDFC is 1000", HttpStatus.NOT_ACCEPTABLE);
        }

    }



    @RequestMapping(value = "/acc/icici/details/{name}",method = RequestMethod.GET)
    public ResponseEntity<Object> detailsAccount(@PathVariable("name")String name){
        return new ResponseEntity<>(ICICIbank.detailsAccount(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/acc/balance/icici/{name}",method = RequestMethod.GET)

    public ResponseEntity<Object> Balance(@PathVariable("name")String name){
        try {
            return new ResponseEntity<>(name + " Account Balance is : " + ICICIbank.Balance(name), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(name + " Account does not exist : " , HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/acc/balance/icici/deposit/{name}/{amount}",method = RequestMethod.GET)

    public ResponseEntity<Object> Deposit(@PathVariable("name")String name, @PathVariable BigDecimal amount){
        try {
            ICICIbank.Deposit(name, amount);
            return new ResponseEntity<>(name + " Deposit is successfully : ", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(name + " Account does not exist : " , HttpStatus.CONFLICT);


        }
    }

    @RequestMapping(value = "/acc/balance/icici/Withdraw/{name}/{amount}",method = RequestMethod.GET)

    public ResponseEntity<Object> Withdraw(@PathVariable("name")String name, @PathVariable BigDecimal amount) throws InsufficientBalanceException {
        try {
            ICICIbank.Withdraw(name, amount);
            return new ResponseEntity<>(name + " Withdraw is successfully : ", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
