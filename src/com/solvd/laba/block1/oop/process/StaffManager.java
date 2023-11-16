package com.solvd.laba.block1.oop.process;

import com.solvd.laba.block1.oop.exception.DoctorIsNotFound;
import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Nurse;
import com.solvd.laba.block1.oop.model.payment.BankSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class StaffManager {
    private static Doctor[] doctors;
    private static Nurse[] nurses;

    private static final Logger LOGGER = LogManager.getLogger(StaffManager.class.getName());

    public StaffManager(Doctor[] doctors, Nurse[] nurses) {
        this.doctors = doctors;
        this.nurses = nurses;
    }

    public boolean addDoctor(Doctor doctor) {
        int i = 0;
        while (doctors[i] != null && i < doctors.length) {
            if (doctors[i].equals(doctor)) {
                LOGGER.warn("This doctor has already worked.");
                return false;
            } else {
                i++;
            }
        }
        if (i == doctors.length) {
            LOGGER.info("We don't need a new doctor.");
            return false;
        } else {
            doctors[i] = doctor;
            return true;
        }
    }

    public Doctor[] getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctor[] doctors) {
        this.doctors = doctors;
    }

    public Doctor[] getDoctorsBySpeciality(String speciality) {
        Doctor[] doctorsBySpeciality = new Doctor[doctors.length];
        int j = 0;
        for (Doctor doctor : doctors) {
            if (doctor.getSpeciality().equals(speciality)) {
                doctorsBySpeciality[j] = doctor;
                j++;
            }
        }
        return doctorsBySpeciality;
    }

    public static Doctor findDoctor(String lastName) throws DoctorIsNotFound {
        Doctor foundDoc = null;
        for (Doctor doctor : doctors) {
            if (doctor.getLastName().equals(lastName)) {
                foundDoc = doctor;
                return foundDoc;
            }
        }

        if (foundDoc == null) {
            throw new DoctorIsNotFound("Doctor " + lastName + " doesn't work here.");
        }

        return foundDoc;
    }

    public Nurse[] getNurses() {
        return nurses;
    }

    public void setNurses(Nurse[] nurses) {
        this.nurses = nurses;
    }

    public static int evaluateDoctor(Doctor doctor) throws DoctorIsNotFound {
        try {
            findDoctor(doctor.getLastName());
            Random random = new Random();
            return random.nextInt(11);
        } catch (DoctorIsNotFound e) {
            LOGGER.error("Doctor " + doctor.getLastName() + " doesn't work here.", e);
            return 0;
        }
    }
}
