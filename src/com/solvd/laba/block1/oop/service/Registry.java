package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.AppointmentStatus;
import com.solvd.laba.block1.oop.enums.WeekDay;
import com.solvd.laba.block1.oop.exception.DoctorIsNotFound;
import com.solvd.laba.block1.oop.exception.IllegalAppointmentId;
import com.solvd.laba.block1.oop.exception.PatientIsNotFound;
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
            throws DoctorIsNotFound, PatientIsNotFound {

        if (findPatient(patient.getLastName()) == null) {
            LOGGER.warn("You should register patient " + patient.getLastName() + "first");
            throw new PatientIsNotFound("You should register patient " + patient.getLastName() + "first");
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

    private Patient findPatient(String lastName) throws PatientIsNotFound {
        try {
            for (Patient patient : patientsInClinic) {
                if (patient.getLastName().equals(lastName)) {
                    return patient;
                }
            }
        } catch (Exception e) {
            LOGGER.error("You should register patient " + lastName + " first", e);
            throw new PatientIsNotFound("You should register patient " + lastName + " first");
        }
        return null;
    }

    public static Appointment findAppointmentById(int id) throws IllegalAppointmentId {
        try {
            for (Appointment appointment : appointmentsList) {
                if (appointment.getId() == id) {
                    return appointment;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Appointment id " + id + " is wrong", e);
            throw new IllegalAppointmentId("Appointment id " + id + " is wrong");
        }
        return null;
    }

    @Override
    public boolean isOpened(WeekDay weekDay, int hour) {
        return hour >= 8 && hour < 19;
    }
}
