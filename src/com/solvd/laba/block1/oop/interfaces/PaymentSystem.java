package com.solvd.laba.block1.oop.interfaces;

import com.solvd.laba.block1.oop.exception.BankIsNotAvailableException;

public interface PaymentSystem {
    void pay(double cost) throws BankIsNotAvailableException;
}
