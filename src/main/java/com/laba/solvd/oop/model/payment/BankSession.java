package com.laba.solvd.oop.model.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class BankSession implements AutoCloseable, Runnable {
    private static final Logger LOGGER = LogManager.getLogger(BankSession.class);
    private int id;

    public BankSession() {
        Random random = new Random();
        this.id = random.nextInt(100);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void close() {
        LOGGER.info("Bank session" + getId() + " is closed.");
    }

    @Override
    public void run() {
        LOGGER.info("Bank session " + getId() + " is run.");
    }
}
