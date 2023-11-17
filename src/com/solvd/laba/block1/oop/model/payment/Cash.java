package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.interfaces.PaymentSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Cash implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(Cash.class.getName());

    @Override
    public void pay(double cost) {
        LOGGER.info(cost + "$ was payed by cash.");
    }
}
