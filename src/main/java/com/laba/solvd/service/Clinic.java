package com.laba.solvd.service;

import com.laba.solvd.enums.AppointmentStatus;
import com.laba.solvd.enums.WeekDay;
import com.laba.solvd.exception.IllegalAppointmentIdException;
import com.laba.solvd.exception.IllegalMedicalReportIdException;
import com.laba.solvd.interfaces.Department;
import com.laba.solvd.interfaces.Evaluation;
import com.laba.solvd.model.Patient;
import com.laba.solvd.process.Appointment;
import com.laba.solvd.process.MedicalReport;
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

    private void addMedicalReport(MedicalReport medicalReport) {
        medicalReportList.add(medicalReport);
        medicalReportCount++;
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
        int markSum = medicalReportList.stream().mapToInt(report ->
                report.getAppointment().getPatient().evaluate()).sum();
        return (medicalReportCount != 0) ? (double) markSum / medicalReportCount : 0;
    }

    @Override
    public String getName() {
        return "Clinic";
    }

}
