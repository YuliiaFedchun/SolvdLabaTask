package com.laba.solvd.model.payment;

import com.laba.solvd.exception.BankIsNotAvailableException;
import com.laba.solvd.interfaces.PaymentSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreditCard implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(CreditCard.class);

    @Override
    public void pay(double cost) {
        try (BankSession bankSession = new Bank().getBankSession()) {
            LOGGER.info(cost + "$ was payed by credit card. Transaction id: " + bankSession.getId());
        } catch (BankIsNotAvailableException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
