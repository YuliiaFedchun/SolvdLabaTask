package com.solvd.laba.block1.task2.service;

import com.solvd.laba.block1.task2.process.Appointment;

public class PayOffice {
    private Registry registry;

    public PayOffice(Registry registry) {
        this.registry = registry;
    }

    public boolean pay (Appointment appointment){
        return false;
    }
    public String checkPaymentStatus(Appointment appointment){
        return null;
    }
}
