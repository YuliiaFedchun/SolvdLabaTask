package com.laba.solvd.model.payment;

import com.laba.solvd.interfaces.PaymentSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Cash implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(Cash.class);

    @Override
    public void pay(double cost) {
        LOGGER.info(cost + "$ was payed by cash.");
    }
}
