package com.solvd.laba.block1.oop.model;

import java.util.Objects;

public class Patient extends Person {
    private String email;
    private Insurance insurance;

    public Patient(String firstName, String lastName, int age, String phoneNumber, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
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

    @Override
    public void showContactInfo() {
        System.out.println("Address: " + this.address + "\n"
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
}
