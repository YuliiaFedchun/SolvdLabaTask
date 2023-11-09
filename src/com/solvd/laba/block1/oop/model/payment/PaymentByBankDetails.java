package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.model.payment.PaymentSystem;
import com.solvd.laba.block1.oop.service.PayOffice;

public class PaymentByBankDetails implements PaymentSystem {
    @Override
    public void pay(double cost) {
        String iban = PayOffice.getBankDetails();
        System.out.println(cost + "$ was payed on the bank account " + iban);
    }
}
