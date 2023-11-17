package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.AppointmentStatus;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.exception.DoctorIsNotFoundException;
import com.solvd.laba.block1.oop.exception.IllegalAppointmentIdException;
import com.solvd.laba.block1.oop.exception.PatientIsNotFoundException;
import com.solvd.laba.block1.oop.interfaces.Department;
import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Patient;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.StaffManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public final class Registry implements Department {

    private StaffManager staff;
    private Patient[] patientsInClinic;
    private static Appointment[] appointmentsList;
    private final int clinicCapacity = 20;
    private final int maxAppointmentsCount = 40;
    private int appointmentsCount;

    private static final Logger LOGGER = LogManager.getLogger(Registry.class.getName());

    public Registry(StaffManager staff) {
        this.staff = staff;
        this.patientsInClinic = new Patient[clinicCapacity];
        appointmentsList = new Appointment[maxAppointmentsCount];
        this.appointmentsCount = 0;
    }

    public Patient[] getPatientsInClinic() {
        return patientsInClinic;
    }

    public void setPatientsInClinic(Patient[] patientsInClinic) {
        this.patientsInClinic = patientsInClinic;
    }

    public Appointment[] getAppointmentsList() {
        return appointmentsList;
    }

    public void setAppointmentsList(Appointment[] appointmentsList) {
        Registry.appointmentsList = appointmentsList;
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
            if (patientsInClinic[i].equals(patient)) {
                LOGGER.warn("This patient has already registered.");
                return false;
            } else {
                i++;
            }
        }
        if (i == patientsInClinic.length) {
            LOGGER.warn("We can't register a new patient.");
            return false;
        } else {
            patientsInClinic[i] = patient;
            LOGGER.info("Patient " + patient.getLastName() + " is registered.");
            return true;
        }
    }

    public static Appointment[] getAppointmentListByDoctor(Doctor doctor) {
        Appointment[] appointments = new Appointment[appointmentsList.length];
        int count = 0;
        for (Appointment appointment : appointmentsList) {
            if (appointment != null && appointment.getDoctor().equals(doctor)) {
                appointments[count] = appointment;
                count++;
            }
        }

        return Arrays.copyOf(appointments, count);
    }

    public int registerAppointment(Doctor doctor, Patient patient, WeekDay weekDay, int timeSlot)
            throws DoctorIsNotFoundException, PatientIsNotFoundException {

        try {
            findPatient(patient.getLastName());
        } catch (PatientIsNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (staff.findDoctor(doctor.getLastName()).isTimeSlotFree(weekDay, timeSlot)) {
            Random random = new Random();

            Appointment appointment = new Appointment(random.nextInt(999), doctor, patient, weekDay, timeSlot);

            appointmentsList[appointmentsCount] = appointment;
            appointmentsCount++;
            appointment.setStatus(AppointmentStatus.PLANED);

            doctor.chooseTimeSlot(weekDay, timeSlot);
            LOGGER.info("Your appointment for " + patient.getLastName() + " is planed. \nAppointment info: \n" +
                    appointment);
            return appointment.getId();
        } else {
            LOGGER.warn("Chosen time slot isn't available.");
        }

        return 0;
    }

    private Patient findPatient(String lastName) throws PatientIsNotFoundException {
        Patient foundPatient = null;
        for (Patient patient : patientsInClinic) {
            if (patient.getLastName().equals(lastName)) {
                foundPatient = patient;
                return patient;
            }
        }
        if (foundPatient == null) {
            throw new PatientIsNotFoundException("You should register patient " + lastName + " first");
        }
        return foundPatient;
    }

    public static Appointment findAppointmentById(int id) throws IllegalAppointmentIdException {
        Appointment foundAppointment = null;
        for (Appointment appointment : appointmentsList) {
            if (appointment.getId() == id) {
                foundAppointment = appointment;
                return foundAppointment;
            }
        }
        if (foundAppointment == null) {
            throw new IllegalAppointmentIdException("Appointment id " + id + " is wrong");
        }
        return foundAppointment;
    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return hour >= 8 && hour < 19;
    }
}
