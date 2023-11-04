package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.model.Patient;

public class Hospital {
    private Patient[] patientsInHospital;
    private int patientsCount = 0;

    public Hospital() {
        this.patientsInHospital = new Patient[20];
    }

    public Patient[] getPatientsInHospital() {
        return patientsInHospital;
    }

    public void setPatientsInHospital(Patient[] patientsInHospital) {
        this.patientsInHospital = patientsInHospital;
    }

    public boolean addPatient(Patient patient) {
        if (findPatient(patient.getLastName()) != null
                && findPatient(patient.getLastName()).equals(patient)) {
            System.out.println("This patient is in the hospital now");
            return false;
        } else {
            patientsInHospital[patientsCount] = patient;
            patientsCount++;
            System.out.println("The patient " + patient.getLastName() + " was admitted to the hospital.");
            return true;
        }
    }

    public Patient findPatient(String lastName) {
        if (patientsCount > 0) {
            for (int i = 0; i <= patientsCount; i++) {
                if (patientsInHospital[i].getLastName().equals(lastName)) {
                    return patientsInHospital[i];
                }
            }
        }
        return null;
    }
}
