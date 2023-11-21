package com.solvd.laba.block1.oop.model.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class BankSession implements AutoCloseable {
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
        LOGGER.info("Bank session is closed.");
    }
}
