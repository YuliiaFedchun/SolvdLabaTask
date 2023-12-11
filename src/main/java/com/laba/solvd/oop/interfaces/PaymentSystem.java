package com.laba.solvd.oop.interfaces;

import com.laba.solvd.oop.exception.BankIsNotAvailableException;

public interface PaymentSystem {
    void pay(double cost) throws BankIsNotAvailableException;
}
