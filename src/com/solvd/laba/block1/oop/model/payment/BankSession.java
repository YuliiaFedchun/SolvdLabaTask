package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class BankSession implements AutoCloseable {
    private int id;
    private static final Logger LOGGER = LogManager.getLogger(BankSession.class.getName());

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
    public void close() throws BankIsNotAvailableException {
        LOGGER.info("Bank session is closed.");
    }
}
