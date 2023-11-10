package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.Evaluation;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.model.Patient;

public final class Hospital implements Department, Evaluation {
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

    public boolean hospitalizePatient(int reportId, Patient patient) {
        if (findPatient(patient.getLastName()) != null
                && findPatient(patient.getLastName()).equals(patient)) {
            System.out.println("This patient is in the hospital now.");
            return false;
        } else if (Clinic.findMedicalReportById(reportId) != null &&
                Clinic.findMedicalReportById(reportId).isHospitalized()) {
            patientsInHospital[patientsCount] = patient;
            patientsCount++;
            System.out.println("The patient " + patient.getLastName() + " was admitted to the hospital.");
            return true;
        } else {
            System.out.println("The patient does not require hospitalization.");
        }
        return false;
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

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return true;
    }

    @Override
    public double getRating() {
        int markSum = 0;
        for (int i = 0; i < patientsCount; i++) {
            markSum += patientsInHospital[i].evaluate();
        }
        if (patientsCount != 0) return markSum / patientsCount;
        else return 0;
    }

}
