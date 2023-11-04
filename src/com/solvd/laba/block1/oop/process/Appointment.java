package com.solvd.laba.block1.oop.process;

import com.solvd.laba.block1.oop.enums.Status;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Patient;

import java.util.Objects;

public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private WeekDay weekDay;
    private int timeSlot;
    private Status status;

    public Appointment(Doctor doctor, Patient patient, WeekDay weekDay, int timeSlot) {
        this.doctor = doctor;
        this.patient = patient;
        this.weekDay = weekDay;
        this.timeSlot = timeSlot;
        this.status = Status.NEW;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment appointment = (Appointment) o;
        return timeSlot == appointment.timeSlot
                && Objects.equals(doctor, appointment.doctor)
                && Objects.equals(patient, appointment.patient)
                && weekDay == appointment.weekDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, weekDay, timeSlot);
    }

    @Override
    public String toString() {
        return "doctor=" + doctor.toString() + "\n" +
                "patient=" + patient.toString() + "\n" +
                "weekDay=" + weekDay +
                ", time=" + (9 + timeSlot / 2) + ":" + (3 * (timeSlot % 2)) + "0";
    }
}
