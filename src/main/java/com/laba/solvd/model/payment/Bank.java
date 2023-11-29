package com.laba.solvd.model.payment;

import com.laba.solvd.exception.BankIsNotAvailableException;

import java.util.Random;

public final class Bank {
    private final BankSession session;

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
