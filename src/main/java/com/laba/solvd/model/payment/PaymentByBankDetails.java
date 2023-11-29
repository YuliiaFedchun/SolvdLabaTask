package com.laba.solvd.model.payment;

import com.laba.solvd.exception.BankIsNotAvailableException;
import com.laba.solvd.interfaces.PaymentSystem;
import com.laba.solvd.service.PayOffice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PaymentByBankDetails implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(PaymentByBankDetails.class);

    @Override
    public void pay(double cost) {
        String iban = PayOffice.getBankDetails();
        try (BankSession bankSession = new Bank().getBankSession()) {
            LOGGER.info(cost + "$ was payed on the bank account " + iban + ". Transaction id: "
                    + bankSession.getId());
        } catch (BankIsNotAvailableException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
