package com.laba.solvd.service;

import com.laba.solvd.enums.AppointmentStatus;
import com.laba.solvd.enums.WeekDay;
import com.laba.solvd.exception.DoctorIsNotFoundException;
import com.laba.solvd.exception.IllegalAppointmentIdException;
import com.laba.solvd.exception.PatientIsNotFoundException;
import com.laba.solvd.interfaces.Department;
import com.laba.solvd.model.Doctor;
import com.laba.solvd.model.Patient;
import com.laba.solvd.process.Appointment;
import com.laba.solvd.process.StaffManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class Registry implements Department {
    private static final Logger LOGGER = LogManager.getLogger(Registry.class);

    private List<Patient> patientsInClinic;
    private static List<Appointment> appointmentsList;

    public Registry() {
        this.patientsInClinic = new ArrayList<>();
        appointmentsList = new ArrayList<>();
    }

    public void registerPatient(Patient patient) {
        if (patientsInClinic.contains(patient)) {
            LOGGER.warn("This patient has already registered.");
        } else {
            patientsInClinic.add(patient);
            LOGGER.info("Patient " + patient.getLastName() + " is registered.");
        }
    }

    public static List<Appointment> getAppointmentListByDoctor(Doctor doctor) {
        return appointmentsList.stream().filter(appointment -> appointment.getDoctor().equals(doctor))
                .collect(Collectors.toList());
    }

    public int registerAppointment(Doctor doctor, Patient patient, WeekDay weekDay, int timeSlot)
            throws DoctorIsNotFoundException {
        try {
            findPatient(patient.getLastName());
        } catch (PatientIsNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (StaffManager.findDoctor(doctor.getLastName()).isTimeSlotFree(weekDay, timeSlot)) {
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
        Patient foundPatient = patientsInClinic.stream()
                .filter(patient -> patient.getLastName().equals(lastName))
                .collect(Collectors.toList()).get(0);
        if (foundPatient != null) {
            return foundPatient;
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

    public List<Appointment> showPlannedAppointments() {
        return appointmentsList.stream()
                .filter(appointment -> appointment.getStatus().equals(AppointmentStatus.PLANED))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return hour >= 8 && hour < 19;
    }


    public List<Patient> getPatientsInClinic() {
        return patientsInClinic;
    }

    public void setPatientsInClinic(List<Patient> patientsInClinic) {
        this.patientsInClinic = patientsInClinic;
    }

    public List<Appointment> getAppointmentsList() {
        return appointmentsList.stream()
                .sorted(Comparator.comparingInt(app -> app.getStatus().getStageNumber()))
                .collect(Collectors.toList());
    }

    public void setAppointmentsList(List<Appointment> appointmentsList) {
        Registry.appointmentsList = appointmentsList;
    }

    @Override
    public String getName() {
        return "Registry";
    }
}
