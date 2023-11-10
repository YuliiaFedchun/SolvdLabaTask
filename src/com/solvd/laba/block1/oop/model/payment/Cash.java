package com.solvd.laba.block1.oop.model.payment;

import com.solvd.laba.block1.oop.model.payment.PaymentSystem;

public class Cash implements PaymentSystem {
    @Override
    public void pay(double cost) {
        System.out.println(cost + "$ was payed by cash.");
    }
}
