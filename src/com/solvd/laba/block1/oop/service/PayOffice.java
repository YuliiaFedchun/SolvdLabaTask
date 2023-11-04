package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.Status;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.Receipt;

public class PayOffice {
    private Receipt[] payments;

    private int receiptsCounter = 0;

    public PayOffice() {
        this.payments = new Receipt[20];
    }

    public Receipt pay(Appointment appointment) {
        Receipt receipt = new Receipt(null, 0.0);

        if (appointment.getStatus().equals(Status.DONE)) {

            double costForPatient = appointment.getDoctor().getConsultationCost() *
                    appointment.getPatient().getInsurance().getFranchise();

            receipt.setPatient(appointment.getPatient());
            receipt.setCost(costForPatient);
            addReceipt(receipt);

            System.out.println("Mr./Mrs. " + appointment.getPatient().getLastName() + ", you should pay: " + costForPatient);

            appointment.setStatus(Status.PAYED);
        }
        return receipt;
    }

    public boolean addReceipt(Receipt receipt) {
        payments[receiptsCounter] = receipt;
        receiptsCounter++;
        return true;
    }
}
