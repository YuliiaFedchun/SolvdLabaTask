package com.laba.solvd.process;

import com.laba.solvd.interfaces.Printable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MedicalReport implements Printable {
    private static final Logger LOGGER = LogManager.getLogger(MedicalReport.class);

    private int reportId;
    private Appointment appointment;
    private String symptom;
    private String diagnosis;
    private String recommendation;
    private boolean hospitalization;

    public MedicalReport(int id, Appointment appointment, String symptom, String diagnosis, String recommendation,
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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
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

    public boolean isHospitalized() {
        return hospitalization;
    }

    public void setHospitalization(boolean hospitalization) {
        this.hospitalization = hospitalization;
    }

    @Override
    public void print() {
        LOGGER.info(
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
