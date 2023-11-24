package com.laba.solvd.interfaces;

import com.laba.solvd.exception.BankIsNotAvailableException;

public interface PaymentSystem {
    void pay(double cost) throws BankIsNotAvailableException;
}
