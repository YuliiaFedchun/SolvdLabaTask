package com.laba.solvd.process;

import com.laba.solvd.exception.DoctorIsNotFoundException;
import com.laba.solvd.model.Doctor;
import com.laba.solvd.model.Nurse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StaffManager {
    private static final Logger LOGGER = LogManager.getLogger(StaffManager.class);

    private static List<Doctor> doctors;
    private static List<Nurse> nurses;

    public StaffManager(List<Doctor> doctors, List<Nurse> nurses) {
        this.doctors = doctors;
        this.nurses = nurses;
    }

    public boolean addDoctor(Doctor doctor) {
        if (doctors.indexOf(doctor) != -1) {
            LOGGER.warn("This doctor has already worked here.");
            return false;
        } else {
            doctors.add(doctor);
            return true;
        }
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Doctor> getDoctorsBySpeciality(String speciality) {
        List<Doctor> doctorsBySpeciality = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpeciality().equals(speciality)) {
                doctorsBySpeciality.add(doctor);
            }
        }
        return doctorsBySpeciality;
    }

    public static Doctor findDoctor(String lastName) throws DoctorIsNotFoundException {
        for (Doctor doctor : doctors) {
            if (doctor.getLastName().equals(lastName)) {
                return doctor;
            }
        }
        throw new DoctorIsNotFoundException("Doctor " + lastName + " doesn't work here.");
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public static int evaluateDoctor(Doctor doctor) {
        try {
            findDoctor(doctor.getLastName());
            Random random = new Random();
            return random.nextInt(11);
        } catch (DoctorIsNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }
}
