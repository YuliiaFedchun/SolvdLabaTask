package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailableException;

import java.util.Random;

public final class Bank {
    private BankSession session;

    public Bank() {
        this.session = new BankSession();
    }

    public BankSession getBankSession() throws BankIsNotAvailableException {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new BankIsNotAvailableException("Bank isn't response. Try later.");
        }
        return session;
    }

}
