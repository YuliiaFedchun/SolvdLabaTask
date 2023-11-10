package com.solvd.laba.block1.oop.process;

import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Nurse;

import java.util.Random;

public class StaffManager {
    private Doctor[] doctors;
    private Nurse[] nurses;

    public StaffManager(Doctor[] doctors, Nurse[] nurses) {
        this.doctors = doctors;
        this.nurses = nurses;
    }

    public boolean addDoctor(Doctor doctor) {
        int i = 0;
        while (doctors[i] != null && i < doctors.length) {
            if (doctors[i].equals(doctor)) {
                System.out.println("This doctor has already worked.");
                return false;
            } else {
                i++;
            }
        }
        if (i == doctors.length) {
            System.out.println("We don't need a new doctor.");
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

    public Doctor findDoctor(String lastName) {
        for (Doctor doctor : doctors) {
            if (doctor.getLastName().equals(lastName)) {
                return doctor;
            }
        }
        return null;
    }

    public Nurse[] getNurses() {
        return nurses;
    }

    public void setNurses(Nurse[] nurses) {
        this.nurses = nurses;
    }

    public static int evaluateDoctor(Doctor doctor) {
        Random random = new Random();
        return random.nextInt(11);
    }
}
