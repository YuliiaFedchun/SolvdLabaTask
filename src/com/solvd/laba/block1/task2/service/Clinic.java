package com.solvd.laba.block1.task2.service;

import com.solvd.laba.block1.task2.model.Patient;
import com.solvd.laba.block1.task2.process.Appointment;
import com.solvd.laba.block1.task2.process.Resume;

import java.util.List;

public class Clinic {
    private Registry registry;
    private List<Resume> resumeList;

    public Clinic(Registry registry) {
        this.registry = registry;
    }

    public Resume visitClinic(Patient patient){
        return null;
    }

    public List<Resume> getResumeList() {
        return resumeList;
    }

}
