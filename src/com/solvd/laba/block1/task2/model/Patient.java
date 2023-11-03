package com.solvd.laba.block1.task2.model;

public class Patient extends Person{
    private Insurance insurance;

    public Patient(String firstName, String lastName, int age, String phoneNumber, String address) {
        super(firstName, lastName, age, phoneNumber, address);
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
