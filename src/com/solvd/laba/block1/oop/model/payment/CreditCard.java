package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailableException;
import com.solvd.laba.block1.oop.interfaces.PaymentSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreditCard implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(CreditCard.class);

    @Override
    public void pay(double cost) {
        try (BankSession bankSession = new Bank().getBankSession()) {
            LOGGER.info(cost + "$ was payed by credit card. Tranzaction id: " + bankSession.getId());
        } catch (BankIsNotAvailableException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
