package com.solvd.laba.block1.task2.process;

import com.solvd.laba.block1.task2.model.Doctor;
import com.solvd.laba.block1.task2.model.Patient;

public class Appointment extends Meeting{
    private int cost;
    private String status;

    public Appointment(Doctor doctor, Patient patient, String data, String time, int cost) {
        this.person1 = doctor;
        this.person2 = patient;
        this.data = data;
        this.time = time;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Meeting plan() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
