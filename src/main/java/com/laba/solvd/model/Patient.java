package com.laba.solvd.model;

import com.laba.solvd.interfaces.PaymentSystem;
import com.laba.solvd.model.payment.Cash;
import com.laba.solvd.model.payment.CreditCard;
import com.laba.solvd.model.payment.PaymentByBankDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Random;


public class Patient extends Person {
    private static final Logger LOGGER = LogManager.getLogger(Patient.class);

    private String email;
    private Insurance insurance;

    public Patient(String firstName, String lastName, int age, String phoneNumber, String address, String email) {
        super(firstName, lastName, age, phoneNumber, address);
        this.email = email;
    }

    public PaymentSystem choosePaymentSystem() {
        Random random = new Random();
        int num = random.nextInt(3);
        if (num == 0) {
            return new Cash();
        } else if (num == 1) {
            return new CreditCard();
        } else {
            return new PaymentByBankDetails();
        }
    }

    @Override
    public void showContactInfo() {
        LOGGER.info("Address: " + this.address + "\n"
                + "Phone number: " + this.phoneNumber + "\n"
                + "Email: " + this.email + "\n");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return firstName.equals(patient.firstName)
                && lastName.equals(patient.lastName)
                && age == patient.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public int evaluate() {
        Random random = new Random();
        return random.nextInt(11);
    }


    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
