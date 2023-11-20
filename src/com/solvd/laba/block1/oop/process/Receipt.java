package com.solvd.laba.block1.oop.process;

import com.solvd.laba.block1.oop.interfaces.Printable;
import com.solvd.laba.block1.oop.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Receipt implements Printable {
    private Patient patient;
    private double cost;

    private static final Logger LOGGER = LogManager.getLogger(Receipt.class);

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
        LOGGER.info("Receipt{" +
                "patient=" + patient.getFirstName() + " " + patient.getLastName() +
                ", cost=" + cost + "$}");
    }
}
