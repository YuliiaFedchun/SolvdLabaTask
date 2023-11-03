package com.solvd.laba.block1.task2.model;

public class Doctor extends Person{
    private String speciality;
    private int[][] schedule;


    public Doctor(String firstName, String lastName, int age, String phoneNumber, String address,
                  String speciality) {
        super(firstName, lastName, age, phoneNumber, address);
        this.speciality = speciality;
        schedule = new int[7][11];
    }

    public String getSpeciality() {
        return speciality;
    }

    public int[][] getSchedule() {
        return schedule;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setSchedule(int[][] schedule) {
        this.schedule = schedule;
    }
}
