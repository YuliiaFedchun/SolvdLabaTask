package com.solvd.laba.block1.oop.process;

import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Nurse;

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
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i].getSpeciality().equals(speciality)) {
                doctorsBySpeciality[j] = doctors[i];
                j++;
            }
        }
        return doctorsBySpeciality;
    }

    public Doctor findDoctor(String lastName) {
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i].getLastName().equals(lastName)) {
                return doctors[i];
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
}
