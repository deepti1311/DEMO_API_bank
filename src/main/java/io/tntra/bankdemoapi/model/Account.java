package io.tntra.bankdemoapi.model;

import java.math.BigDecimal;

public class Account {

    private  String id;
    private  String name;

    public BigDecimal balance;
    public BigDecimal min_balance = BigDecimal.valueOf(0);
    private BigDecimal overDraft;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMin_balance() {
        return min_balance;
    }

    public void setMin_balance(BigDecimal min_balance) {
        this.min_balance = min_balance;

    }

    public BigDecimal getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(BigDecimal overDraft) {
        this.overDraft = overDraft;
    }




}
