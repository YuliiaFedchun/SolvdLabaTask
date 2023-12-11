package com.laba.solvd.oop.service;

import com.laba.solvd.oop.enums.WeekDay;
import com.laba.solvd.oop.exception.HospitalIsFullException;
import com.laba.solvd.oop.exception.IllegalMedicalReportIdException;
import com.laba.solvd.oop.interfaces.Department;
import com.laba.solvd.oop.interfaces.Evaluation;
import com.laba.solvd.oop.list.MyLinkedList;
import com.laba.solvd.oop.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Hospital implements Department, Evaluation {
    private static final Logger LOGGER = LogManager.getLogger(Hospital.class);

    private MyLinkedList<Patient> patientsInHospital;
    private final int hospitalCapacity = 20;

    public Hospital() {
        this.patientsInHospital = new MyLinkedList<>();
    }


    public boolean hospitalizePatient(int reportId, Patient patient)
            throws IllegalMedicalReportIdException {
        try {
            hospitalIsFull();
        } catch (HospitalIsFullException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (findPatient(patient.getLastName()) != null
                && findPatient(patient.getLastName()).equals(patient)) {
            LOGGER.info("This patient is in the hospital now.");
            return false;
        } else if (Clinic.findMedicalReportById(reportId).isHospitalized()) {
            patientsInHospital.add(patient);
            LOGGER.info("The patient " + patient.getLastName() + " was admitted to the hospital.");
            return true;
        } else {
            LOGGER.info("The patient does not require hospitalization.");
        }
        return false;
    }

    private boolean hospitalIsFull() throws HospitalIsFullException {
        if (hospitalCapacity == patientsInHospital.size()) {
            throw new HospitalIsFullException("Hospital is full. We can't hospitalized a new patient.");
        }
        return false;
    }

    public Patient findPatient(String lastName) {
        if (patientsInHospital.size() != 0) {
            for (Patient patient : patientsInHospital) {
                if (patient.getLastName().equals(lastName)) {
                    return patient;
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
        if (patientsInHospital.size() != 0) {
            for (Patient patient : patientsInHospital) {
                markSum += patient.evaluate();
            }
            return markSum / patientsInHospital.size();
        } else return 0;
    }

    public MyLinkedList<Patient> getPatientsInHospital() {
        return patientsInHospital;
    }

    public void setPatientsInHospital(MyLinkedList<Patient> patientsInHospital) {
        this.patientsInHospital = patientsInHospital;
    }

    @Override
    public String getName() {
        return "Hospital";
    }


}
