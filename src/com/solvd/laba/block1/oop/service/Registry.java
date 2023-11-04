package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.Status;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Patient;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.StaffManager;

public class Registry {

    protected StaffManager staff;
    protected Patient[] patientsInClinic;
    protected Appointment[] appointments;

    private int appointmentsCount;

    public Registry(StaffManager staff) {
        this.staff = staff;
        this.patientsInClinic = new Patient[20];
        this.appointments = new Appointment[40];
        this.appointmentsCount = 0;
    }

    public Patient[] getPatientsInClinic() {
        return patientsInClinic;
    }

    public void setPatientsInClinic(Patient[] patientsInClinic) {
        this.patientsInClinic = patientsInClinic;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment[] appointments) {
        this.appointments = appointments;
    }

    public int getAppointmentsCount() {
        return appointmentsCount;
    }

    public void setAppointmentsCount(int appointmentsCount) {
        this.appointmentsCount = appointmentsCount;
    }

    public boolean registerPatient(Patient patient) {
        int i = 0;
        while (patientsInClinic[i] != null && i < patientsInClinic.length) {
            if (patientsInClinic[i].equals(patientsInClinic)) {
                System.out.println("This patient has already registered.");
                return false;
            } else {
                i++;
            }
        }
        if (i == patientsInClinic.length) {
            System.out.println("We can't register a new patient.");
            return false;
        } else {
            patientsInClinic[i] = patient;
            System.out.println("Patient " + patient.getLastName() + " is registered.");
            return true;
        }
    }

    public Appointment registerAppointment(Doctor doctor, Patient patient, WeekDay weekDay, int timeSlot) {
        if (staff.findDoctor(doctor.getLastName()) == null) {
            System.out.println("This doctor doesn't work here.");
            return null;
        }

        if (findPatient(patient.getLastName()) == null) {
            System.out.println("You should register this patient first");
            return null;
        }

        if (staff.findDoctor(doctor.getLastName()).isTimeSlotFree(weekDay, timeSlot)) {

            Appointment appointment = new Appointment(doctor, patient, weekDay, timeSlot);

            appointments[appointmentsCount] = appointment;
            appointmentsCount++;
            appointment.setStatus(Status.PLANED);

            doctor.chooseTimeSlot(weekDay, timeSlot);
            System.out.println("Your appointment for " + patient.getLastName() + " is planed. \nAppointment info: \n" +
                    appointment.toString());
            return appointment;
        } else {
            System.out.println("Chosen time slot isn't available.");
        }

        return null;
    }

    private Patient findPatient(String lastName) {
        for (int i = 0; i < patientsInClinic.length; i++) {
            if (patientsInClinic[i].getLastName().equals(lastName)) {
                return patientsInClinic[i];
            }
        }
        return null;
    }

}
