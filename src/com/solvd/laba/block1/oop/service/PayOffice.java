package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.AppointmentStatus;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.Receipt;

public class PayOffice implements Department{
    private Receipt[] payments;

    private int receiptsCounter = 0;

    private static final String IBAN = "UA25328209123400000056789";

    public PayOffice() {
        this.payments = new Receipt[20];
    }

    public Receipt acceptPayment(int appointmentId) {
        Receipt receipt = new Receipt(null, 0.0);

        Appointment appointment = Registry.findAppointmentById(appointmentId);

        if (appointment.getStatus().equals(AppointmentStatus.DONE)) {

            double costForPatient = appointment.getDoctor().getConsultationCost() *
                    appointment.getPatient().getInsurance().getFranchise();

            appointment.getPatient().choosePaymentSystem().pay(costForPatient);

            receipt.setPatient(appointment.getPatient());
            receipt.setCost(costForPatient);
            addReceipt(receipt);

            System.out.println("Mr./Mrs. " + appointment.getPatient().getLastName() + ", you should pay: "
                                + costForPatient + "$");

            appointment.setStatus(AppointmentStatus.PAYED);
        }
        return receipt;
    }

    private boolean addReceipt(Receipt receipt) {
        payments[receiptsCounter] = receipt;
        receiptsCounter++;
        return true;
    }

    public static String getBankDetails(){
        return IBAN;
    }
    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        if (weekDay != WeekDay.SAT && weekDay != WeekDay.SUN &&
                hour >= 9 && hour < 19) {
            return true;
        }
        return false;
    }
}
