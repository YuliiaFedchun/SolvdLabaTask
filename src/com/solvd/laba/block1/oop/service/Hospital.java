package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.interfaces.Department;
import com.solvd.laba.block1.oop.interfaces.Evaluation;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.exception.HospitalIsFull;
import com.solvd.laba.block1.oop.exception.IllegalMedicalReportId;
import com.solvd.laba.block1.oop.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Hospital implements Department, Evaluation {
    private Patient[] patientsInHospital;
    private int patientsCount = 0;
    private final int hospitalCapacity = 20;

    private static final Logger LOGGER = LogManager.getLogger(Hospital.class.getName());

    public Hospital() {
        this.patientsInHospital = new Patient[hospitalCapacity];
    }

    public Patient[] getPatientsInHospital() {
        return patientsInHospital;
    }

    public void setPatientsInHospital(Patient[] patientsInHospital) {
        this.patientsInHospital = patientsInHospital;
    }

    public boolean hospitalizePatient(int reportId, Patient patient) throws HospitalIsFull, IllegalMedicalReportId {
        if (hospitalCapacity == patientsCount) {
            LOGGER.error("Hospital is full. We can't hospitalized the patient.");
            throw new HospitalIsFull("Hospital is full. We can't hospitalized the patient.");
        }
        if (findPatient(patient.getLastName()) != null
                && findPatient(patient.getLastName()).equals(patient)) {
            LOGGER.info("This patient is in the hospital now.");
            return false;
        } else if (Clinic.findMedicalReportById(reportId).isHospitalized()) {
            patientsInHospital[patientsCount] = patient;
            patientsCount++;
            LOGGER.info("The patient " + patient.getLastName() + " was admitted to the hospital.");
            return true;
        } else {
            LOGGER.info("The patient does not require hospitalization.");
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
