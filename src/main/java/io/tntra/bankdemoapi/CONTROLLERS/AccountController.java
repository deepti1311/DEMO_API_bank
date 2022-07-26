package io.tntra.bankdemoapi.CONTROLLERS;


import io.tntra.bankdemoapi.SERVICES.AccountService;
import io.tntra.bankdemoapi.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {

    @Autowired
    AccountService AccountService;

    @RequestMapping(value = "/acc")
    public ResponseEntity<Object> getAccount() {
        return new ResponseEntity<>(AccountService.getAccount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/acc", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
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


}
