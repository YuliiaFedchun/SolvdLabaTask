package com.solvd.laba.block1.oop.model;

public class Nurse extends Person {
    public Nurse(String firstName, String lastName, int age, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    @Override
    public void showContactInfo() {
        System.out.println("Address: " + this.address + "\n"
                + "Phone number: " + this.phoneNumber);
    }
}
