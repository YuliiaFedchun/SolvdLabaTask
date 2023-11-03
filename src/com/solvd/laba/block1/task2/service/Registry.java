package com.solvd.laba.block1.task2.service;

import com.solvd.laba.block1.task2.model.Doctor;
import com.solvd.laba.block1.task2.model.Patient;
import com.solvd.laba.block1.task2.process.Appointment;
import com.solvd.laba.block1.task2.process.StaffManager;

import java.util.List;

public class Registry {

    protected StaffManager staff;
    private List<Patient> patientList;
    private List<Appointment> appointmentList;

    public Registry(StaffManager staff) {
        this.staff = staff;
    }

    public boolean registerPatient(Patient patient){
        return false;
    }

    public Appointment registerAppointment(Doctor doctor, Patient patient, String data, String time){
        return null;
    }

    public boolean cancelAppointment(Appointment appointment){
        return false;
    }
}
