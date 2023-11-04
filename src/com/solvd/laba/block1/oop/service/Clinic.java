package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.Status;
import com.solvd.laba.block1.oop.model.Patient;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.Resume;

public class Clinic {
    private Resume[] resumeList;
    private int resumeCount;

    public Clinic() {
        this.resumeList = new Resume[40];
        resumeCount = 0;
    }

    public Resume visitClinic(Appointment appointment, String symptoms, String diagnosis, String recommendation) {
        if (appointment.getStatus().equals(Status.PLANED)) {
            appointment.setStatus(Status.DONE);
            Resume resume = new Resume(appointment, symptoms, diagnosis, recommendation);

            resumeList[resumeCount] = resume;
            resumeCount++;

            System.out.println("Mr./Mrs. " + appointment.getPatient().getLastName() + ", thanks for your visit. \n" +
                    "Your recommendation and treatment: " + recommendation);

            return resume;
        }
        return null;
    }

    public Resume[] getResumeList() {
        return resumeList;
    }

    public void setResumeList(Resume[] resumeList) {
        this.resumeList = resumeList;
    }
}
