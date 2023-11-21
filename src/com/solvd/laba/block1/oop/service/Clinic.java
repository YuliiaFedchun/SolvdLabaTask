package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.AppointmentStatus;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.exception.IllegalAppointmentIdException;
import com.solvd.laba.block1.oop.exception.IllegalMedicalReportIdException;
import com.solvd.laba.block1.oop.interfaces.Department;
import com.solvd.laba.block1.oop.interfaces.Evaluation;
import com.solvd.laba.block1.oop.model.Patient;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.MedicalReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;


public final class Clinic implements Department, Evaluation {
    private static final Logger LOGGER = LogManager.getLogger(Clinic.class);

    private static List<MedicalReport> medicalReportList;
    private int medicalReportCount;

    public Clinic() {
        medicalReportList = new LinkedList<>();
        medicalReportCount = 0;
    }

    public int admitPatient(int appointmentId, Patient patient, String symptom) {
        Appointment appointment = null;
        try {
            appointment = Registry.findAppointmentById(appointmentId);
        } catch (IllegalAppointmentIdException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (appointment != null && appointment.getStatus().equals(AppointmentStatus.PLANED)
                && appointment.getPatient().equals(patient)) {
            MedicalReport medicalReport = appointment.getDoctor().makeConsultation(appointment, symptom);

            addMedicalReport(medicalReport);

            appointment.setStatus(AppointmentStatus.DONE);

            LOGGER.info("Mr./Mrs. " + appointment.getPatient().getLastName() + ", thanks for your visit. \n" +
                    "Your recommendation and treatment: " + medicalReport.getRecommendation());

            return medicalReport.getReportId();
        }

        return 0;
    }

    private boolean addMedicalReport(MedicalReport medicalReport) {
        medicalReportList.add(medicalReport);
        medicalReportCount++;
        return true;
    }

    public List<MedicalReport> getMedicalReportList() {
        return medicalReportList;
    }

    public void setMedicalReportList(List<MedicalReport> medicalReportList) {
        Clinic.medicalReportList = medicalReportList;
    }

    public static MedicalReport findMedicalReportById(int id) throws IllegalMedicalReportIdException {
        for (MedicalReport medicalReport : medicalReportList) {
            if (medicalReport.getReportId() == id) {
                return medicalReport;
            }
        }
        throw new IllegalMedicalReportIdException("Medical report id " + id + " is wrong.");

    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return hour >= 9 && hour < 14;
    }

    @Override
    public double getRating() {
        int markSum = 0;
        for (MedicalReport report : medicalReportList) {
            markSum += report.getAppointment().getPatient().evaluate();
        }
        if (medicalReportCount != 0) return markSum / medicalReportCount;
        else return 0;
    }

}
