package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailable;

import java.util.Random;

public final class Bank  {
    private BankSession session;

    public Bank() {
        this.session = new BankSession();
    }

    public BankSession getBankSession() throws BankIsNotAvailable {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new BankIsNotAvailable("Bank isn't response. Try later.");
        }
        return session;
    }

}
