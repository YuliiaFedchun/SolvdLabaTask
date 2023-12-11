package com.laba.solvd.oop.service;

import com.laba.solvd.oop.enums.AppointmentStatus;
import com.laba.solvd.oop.enums.WeekDay;
import com.laba.solvd.oop.exception.BankIsNotAvailableException;
import com.laba.solvd.oop.exception.IllegalAppointmentIdException;
import com.laba.solvd.oop.interfaces.Department;
import com.laba.solvd.oop.interfaces.functional.Profit;
import com.laba.solvd.oop.process.Appointment;
import com.laba.solvd.oop.process.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public final class PayOffice implements Department {
    private static final Logger LOGGER = LogManager.getLogger(PayOffice.class);

    private Set<Receipt> payments;
    private static final String IBAN;

    static {
        StringBuilder prop = new StringBuilder("UA");
        Random random = new Random();
        for (int i = 0; i < 23; i++) {
            prop.append(random.nextInt(10));
        }
        IBAN = prop.toString();
    }

    public PayOffice() {
        this.payments = new HashSet<>();
    }

    public Receipt acceptPayment(int appointmentId) throws BankIsNotAvailableException {
        Receipt receipt = new Receipt(null, 0.0);

        Appointment appointment = null;
        try {
            appointment = Registry.findAppointmentById(appointmentId);
        } catch (IllegalAppointmentIdException e) {
            LOGGER.error(e.getMessage(), e);
        }

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
        return payments.add(receipt);
    }

    public static String getBankDetails() {
        return IBAN;
    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return weekDay != WeekDay.SAT && weekDay != WeekDay.SUN &&
                hour >= 9 && hour < 19;
    }

    public void calculateProfit() {
        Profit<Receipt, Double> profit = (payments) ->
                payments.stream()
                        .mapToDouble(receipt -> receipt.getCost())
                        .sum();
        Double clinicProfit = profit.calculate(payments);
        LOGGER.info("Profit of the clinic: " + clinicProfit + "$.");
    }

    @Override
    public String getName() {
        return "PayOffice";
    }
}
