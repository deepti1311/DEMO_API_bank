package io.tntra.bankdemoapi.CONTROLLERS;

import io.tntra.bankdemoapi.Exceptions.InsufficientBalanceException;
import io.tntra.bankdemoapi.Exceptions.minimumbalanceException;
import io.tntra.bankdemoapi.SERVICES.HDFCbank_SERVICE;
import io.tntra.bankdemoapi.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@RestController
public class HDFC_CONTROLLER {

    final
    HDFCbank_SERVICE HDFCbank;

    public HDFC_CONTROLLER(HDFCbank_SERVICE HDFCbank) {
        this.HDFCbank = HDFCbank;
    }

    @RequestMapping(value = "/acc/hdfc", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccount() {
        return new ResponseEntity<>(HDFCbank.getAccount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/acc/hdfc", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody Account account) throws minimumbalanceException {
      try{  System.out.println(account);
        HDFCbank.createAccount(account);
        return new ResponseEntity<>(" Account is created successfully", HttpStatus.OK);
      }catch (minimumbalanceException e){
          System.out.println(e.getMessage());
          return  new ResponseEntity<>("Minimum balance for HDFC is 1000", HttpStatus.NOT_ACCEPTABLE);
      }

    }



    @RequestMapping(value = "/acc/hdfc/details/{name}",method = RequestMethod.GET)
    public ResponseEntity<Object> detailsAccount(@PathVariable("name")String name){
        return new ResponseEntity<>(HDFCbank.detailsAccount(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/acc/balance/hdfc/{name}",method = RequestMethod.GET)

    public ResponseEntity<Object> Balance(@PathVariable("name")String name){
      try {
          return new ResponseEntity<>(name + " Account Balance is : " + HDFCbank.Balance(name), HttpStatus.OK);
      }catch (Exception e){
          System.out.println(e.getMessage());
          return new ResponseEntity<>(name + " Account does not exist : " , HttpStatus.BAD_REQUEST);
      }
    }



    @RequestMapping(value = "/acc/balance/hdfc/deposit/{name}/{amount}",method = RequestMethod.GET)

    public ResponseEntity<Object> Deposit(@PathVariable("name")String name, @PathVariable BigDecimal amount){
       try {
           HDFCbank.Deposit(name, amount);
           return new ResponseEntity<>(name + " Deposit is successfully : ", HttpStatus.OK);
       }catch (Exception e){
           System.out.println(e.getMessage());
           return new ResponseEntity<>(name + " Account does not exist : " , HttpStatus.CONFLICT);


       }
    }

    @RequestMapping(value = "/acc/balance/hdfc/Withdraw/{name}/{amount}",method = RequestMethod.GET)

    public ResponseEntity<Object> Withdraw(@PathVariable("name")String name, @PathVariable BigDecimal amount) throws InsufficientBalanceException {
       try {
           HDFCbank.Withdraw(name, amount);
           return new ResponseEntity<>(name + " Withdraw is successfully : ", HttpStatus.OK);
       }catch (Exception e){
           System.out.println(e.getMessage());
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
       }
    }
}
