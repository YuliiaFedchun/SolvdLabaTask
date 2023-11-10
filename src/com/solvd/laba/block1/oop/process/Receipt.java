package com.solvd.laba.block1.oop.process;

import com.solvd.laba.block1.oop.model.Patient;

public class Receipt implements Printable {
    private Patient patient;
    private double cost;

    public Receipt(Patient patient, double cost) {
        this.patient = patient;
        this.cost = cost;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public void print() {
        System.out.println("Receipt{" +
                "patient=" + patient.getFirstName() + " " + patient.getLastName() +
                ", cost=" + cost + "$}");
    }
}
