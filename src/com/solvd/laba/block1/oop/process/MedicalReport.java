package com.solvd.laba.block1.oop.process;

import com.solvd.laba.block1.oop.enums.Diagnosis;
import com.solvd.laba.block1.oop.enums.Symptom;

public class MedicalReport implements Printable{
    private int reportId;
    private Appointment appointment;
    private Symptom symptom;
    private Diagnosis diagnosis;
    private String recommendation;
    private boolean hospitalization;

    public MedicalReport(int id, Appointment appointment, Symptom symptom, Diagnosis diagnosis, String recommendation,
                         boolean hospitalization) {
        this.reportId = id;
        this.appointment = appointment;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.recommendation = recommendation;
        this.hospitalization = hospitalization;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public boolean isHospitalized() {
        return hospitalization;
    }

    public void setHospitalization(boolean hospitalization) {
        this.hospitalization = hospitalization;
    }

    @Override
    public void print() {
        System.out.println(
                "MedicalReport{" +
                "id=" + reportId +
                ", symptom=" + symptom +
                ", diagnosis=" + diagnosis +
                ", recommendation='" + recommendation + '\'' +
                ", hospitalization=" + hospitalization +
                '}'
        );
    }
}
