package com.solvd.laba.block1.oop.process;

public class Resume {
    private Appointment appointment;
    private String symptoms;
    private String diagnosis;
    private String recommendation;

    public Resume(Appointment appointment, String symptoms, String diagnosis, String recommendation) {
        this.appointment = appointment;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.recommendation = recommendation;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

}
