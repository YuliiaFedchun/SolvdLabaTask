package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailable;
import com.solvd.laba.block1.oop.interfaces.PaymentSystem;
import com.solvd.laba.block1.oop.service.PayOffice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PaymentByBankDetails implements PaymentSystem {
    private static final Logger LOGGER = LogManager.getLogger(PaymentByBankDetails.class.getName());
    @Override
    public void pay(double cost) throws BankIsNotAvailable {
        String iban = PayOffice.getBankDetails();
        try(BankSession bankSession = new Bank().getBankSession()) {
            LOGGER.info(cost + "$ was payed on the bank account " + iban + ". Tranzaction id: "
                    + bankSession.getId());
        } catch (BankIsNotAvailable e) {
            LOGGER.error("Bank isn't response. Try later.", e);
        }
    }

}
