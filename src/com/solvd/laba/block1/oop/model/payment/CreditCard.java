package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailable;
import com.solvd.laba.block1.oop.interfaces.PaymentSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreditCard implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(CreditCard.class.getName());
    @Override
    public void pay(double cost) throws BankIsNotAvailable {
        try(BankSession bankSession = new Bank().getBankSession()) {
            LOGGER.info(cost + "$ was payed by credit card.");
        } catch (Exception e) {
            LOGGER.error("Bank isn't response. Try later.", e);
            throw new BankIsNotAvailable("Bank isn't response. Try later.");
        }
    }
}
