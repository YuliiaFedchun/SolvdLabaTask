package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailableException;
import com.solvd.laba.block1.oop.interfaces.PaymentSystem;
import com.solvd.laba.block1.oop.service.PayOffice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PaymentByBankDetails implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(PaymentByBankDetails.class);

    @Override
    public void pay(double cost) {
        String iban = PayOffice.getBankDetails();
        try (BankSession bankSession = new Bank().getBankSession()) {
            LOGGER.info(cost + "$ was payed on the bank account " + iban + ". Tranzaction id: "
                    + bankSession.getId());
        } catch (BankIsNotAvailableException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
