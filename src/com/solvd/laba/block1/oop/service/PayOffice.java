package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.AppointmentStatus;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.exception.BankIsNotAvailable;
import com.solvd.laba.block1.oop.exception.IllegalAppointmentId;
import com.solvd.laba.block1.oop.interfaces.Department;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public final class PayOffice implements Department {
    private Receipt[] payments;
    private int receiptsCounter = 0;
    private static final int maxReceiptCount = 20;
    private static final String IBAN;

    private static final Logger LOGGER = LogManager.getLogger(PayOffice.class.getName());

    static {
        StringBuilder prop = new StringBuilder("UA");
        Random random = new Random();
        for (int i = 0; i < 23; i++) {
            prop.append(random.nextInt(10));
        }
        IBAN = prop.toString();
    }

    public PayOffice() {
        this.payments = new Receipt[maxReceiptCount];
    }

    public Receipt acceptPayment(int appointmentId) throws IllegalAppointmentId, BankIsNotAvailable {
        Receipt receipt = new Receipt(null, 0.0);

        Appointment appointment = Registry.findAppointmentById(appointmentId);

        if (appointment != null && appointment.getStatus().equals(AppointmentStatus.DONE)) {

            double costForPatient = appointment.getDoctor().getConsultationCost() *
                    appointment.getPatient().getInsurance().getFranchise();

            LOGGER.info("Mr./Mrs. " + appointment.getPatient().getLastName() + ", you should pay: "
                    + costForPatient + "$");

            appointment.getPatient().choosePaymentSystem().pay(costForPatient);

            receipt.setPatient(appointment.getPatient());
            receipt.setCost(costForPatient);
            addReceipt(receipt);

            appointment.setStatus(AppointmentStatus.PAYED);
        }
        return receipt;
    }

    private boolean addReceipt(Receipt receipt) {
        payments[receiptsCounter] = receipt;
        receiptsCounter++;
        return true;
    }

    public static String getBankDetails() {
        return IBAN;
    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return weekDay != WeekDay.SAT && weekDay != WeekDay.SUN &&
                hour >= 9 && hour < 19;
    }
}
