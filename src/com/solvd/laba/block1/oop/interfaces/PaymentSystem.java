package com.solvd.laba.block1.oop.interfaces;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailable;

public interface PaymentSystem {
    void pay(double cost) throws BankIsNotAvailable;
}
