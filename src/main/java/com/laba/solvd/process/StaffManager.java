package com.laba.solvd.process;

import com.laba.solvd.enums.DoctorSpeciality;
import com.laba.solvd.exception.DoctorIsNotFoundException;
import com.laba.solvd.interfaces.BestWorker;
import com.laba.solvd.model.Doctor;
import com.laba.solvd.model.Nurse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


public class StaffManager {
    private static final Logger LOGGER = LogManager.getLogger(StaffManager.class);

    private static List<Doctor> doctors;
    private static List<Nurse> nurses;

    public StaffManager(List<Doctor> doctors, List<Nurse> nurses) {
        this.doctors = doctors;
        this.nurses = nurses;
    }

    public boolean addDoctor(Doctor doctor) {
        if (doctors.contains(doctor)) {
            LOGGER.warn("This doctor has already worked here.");
            return false;
        } else {
            doctors.add(doctor);
            return true;
        }
    }

    public List<Doctor> getDoctorsBySpeciality(DoctorSpeciality speciality) {
        return doctors.stream().filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    public static Doctor findDoctor(String lastName) throws DoctorIsNotFoundException {
        Doctor foundDoctor = doctors.stream().filter(doctor -> doctor.getLastName().equals(lastName))
                .collect(Collectors.toList()).get(0);
        if (foundDoctor != null) {
            return foundDoctor;
        }
        throw new DoctorIsNotFoundException("Doctor " + lastName + " doesn't work here.");
    }


    public static int evaluateDoctor(Doctor doctor) {
        try {
            findDoctor(doctor.getLastName());
            return new Random().nextInt(11);
        } catch (DoctorIsNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }

    public void rewardBestDoctor() {
        BestWorker<Doctor, Doctor> bestWorker = (doctors) -> {
            Optional<Doctor> max = doctors.stream()
                    .max(Comparator.comparingInt(doctor -> evaluateDoctor(doctor)));

            if (!max.isEmpty()) {
                return max.get();
            } else {
                throw new NullPointerException("The best doctor is not found");
            }
        };

        Doctor bestDoctor = bestWorker.determine(doctors);
        LOGGER.info("The best doctor " + bestDoctor.getLastName() + ". He/she gets the reward: "
                + bestDoctor.getConsultationCost() * 10 + "$.");
    }


    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }


    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

}
