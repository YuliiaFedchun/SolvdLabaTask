package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.Evaluation;
import com.solvd.laba.block1.oop.enums.AppointmentStatus;
import com.solvd.laba.block1.oop.enums.Symptom;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.model.Patient;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.MedicalReport;

public final class Clinic implements Department, Evaluation {
    private static MedicalReport[] medicalReportList;
    private int medicalReportCount;

    public Clinic() {
        medicalReportList = new MedicalReport[40];
        medicalReportCount = 0;
    }

    public int admitPatient(int appointmentId, Patient patient, Symptom symptom) {
        Appointment appointment = Registry.findAppointmentById(appointmentId);
        if (appointment != null && appointment.getStatus().equals(AppointmentStatus.PLANED) && appointment.getPatient().equals(patient)) {
            MedicalReport medicalReport = appointment.getDoctor().makeConsultation(appointment, symptom);

            addMedicalReport(medicalReport);

            appointment.setStatus(AppointmentStatus.DONE);

            System.out.println("Mr./Mrs. " + appointment.getPatient().getLastName() + ", thanks for your visit. \n" +
                    "Your recommendation and treatment: " + medicalReport.getRecommendation());

            return medicalReport.getReportId();
        }
        return 0;
    }

    private boolean addMedicalReport(MedicalReport medicalReport) {
        medicalReportList[medicalReportCount] = medicalReport;
        medicalReportCount++;
        return true;
    }

    public MedicalReport[] getMedicalReportList() {
        return medicalReportList;
    }

    public void setMedicalReportList(MedicalReport[] medicalReportList) {
        Clinic.medicalReportList = medicalReportList;
    }

    public static MedicalReport findMedicalReportById(int id) {
        for (MedicalReport medicalReport : medicalReportList) {
            if (medicalReport.getReportId() == id) {
                return medicalReport;
            }
        }
        return null;
    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return hour >= 9 && hour < 14;
    }

    @Override
    public double getRating() {
        int markSum = 0;
        for (int i = 0; i < medicalReportCount; i++) {
            markSum += medicalReportList[i].getAppointment().getPatient().evaluate();
        }
        if (medicalReportCount != 0) return markSum / medicalReportCount;
        else return 0;
    }

}
