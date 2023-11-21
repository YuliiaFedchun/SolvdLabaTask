package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.AppointmentStatus;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.exception.DoctorIsNotFoundException;
import com.solvd.laba.block1.oop.exception.IllegalAppointmentIdException;
import com.solvd.laba.block1.oop.exception.PatientIsNotFoundException;
import com.solvd.laba.block1.oop.interfaces.Department;
import com.solvd.laba.block1.oop.list.MyLinkedList;
import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Patient;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.StaffManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class Registry implements Department {

    private StaffManager staff;
    private List<Patient> patientsInClinic;
    private static List<Appointment> appointmentsList;

    private static final Logger LOGGER = LogManager.getLogger(Registry.class);

    public Registry(StaffManager staff) {
        this.staff = staff;
        this.patientsInClinic = new ArrayList<>();
        appointmentsList = new ArrayList<>();
    }

    public List<Patient> getPatientsInClinic() {
        return patientsInClinic;
    }

    public void setPatientsInClinic(List<Patient> patientsInClinic) {
        this.patientsInClinic = patientsInClinic;
    }

    public List<Appointment> getAppointmentsList() {
        return appointmentsList;
    }

    public void setAppointmentsList(List<Appointment> appointmentsList) {
        Registry.appointmentsList = appointmentsList;
    }

    public void registerPatient(Patient patient) {
        if (patientsInClinic.indexOf(patient) != -1) {
            LOGGER.warn("This patient has already registered.");
            } else {
            patientsInClinic.add(patient);
            LOGGER.info("Patient " + patient.getLastName() + " is registered.");
        }
    }

    public static List<Appointment> getAppointmentListByDoctor(Doctor doctor) {
        List<Appointment> doctorAppointments = new ArrayList<>();

        for (Appointment appointment : appointmentsList) {
            if (appointment.getDoctor().equals(doctor)) {
                doctorAppointments.add(appointment);
            }
        }

        return doctorAppointments;
    }

    public int registerAppointment(Doctor doctor, Patient patient, WeekDay weekDay, int timeSlot)
            throws DoctorIsNotFoundException {
        try {
            findPatient(patient.getLastName());
        } catch (PatientIsNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (staff.findDoctor(doctor.getLastName()).isTimeSlotFree(weekDay, timeSlot)) {
            Random random = new Random();

            Appointment appointment = new Appointment(random.nextInt(999), doctor, patient, weekDay, timeSlot);

            appointmentsList.add(appointment);
            appointment.setStatus(AppointmentStatus.PLANED);

            doctor.chooseTimeSlot(weekDay, timeSlot);
            LOGGER.info("Your appointment for " + patient.getLastName() + " is planed. \nAppointment info: \n" +
                    appointment);
            return appointment.getId();
        } else {
            LOGGER.warn("Chosen time slot isn't available.");
            return 0;
        }
    }

    private Patient findPatient(String lastName) throws PatientIsNotFoundException {
        for (Patient patient : patientsInClinic) {
            if (patient.getLastName().equals(lastName)) {
                return patient;
            }
        }
        throw new PatientIsNotFoundException("You should register patient " + lastName + " first");

    }

    public static Appointment findAppointmentById(int id) throws IllegalAppointmentIdException {
        for (Appointment appointment : appointmentsList) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        throw new IllegalAppointmentIdException("Appointment id " + id + " is wrong");
    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return hour >= 8 && hour < 19;
    }
}
