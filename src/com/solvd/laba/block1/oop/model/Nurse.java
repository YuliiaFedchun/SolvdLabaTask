package com.solvd.laba.block1.oop.model;

import com.solvd.laba.block1.oop.enums.DayType;
import com.solvd.laba.block1.oop.interfaces.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Nurse extends Person implements Worker {
    private int experienceYears;
    private int[] schedule;

    private static final Logger LOGGER = LogManager.getLogger(Nurse.class);

    public Nurse(String firstName, String lastName, int age, String phoneNumber, String address, int experienceYears) {
        super(firstName, lastName, age, phoneNumber, address);
        this.experienceYears = experienceYears;
        this.schedule = new int[7];
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int[] getSchedule() {
        return schedule;
    }

    public void setSchedule(int[] schedule) {
        this.schedule = schedule;
    }

    @Override
    public void showContactInfo() {
        LOGGER.info("Address: " + this.address + "\n"
                + "Phone number: " + this.phoneNumber);
    }

    @Override
    public void showProfessionalInfo() {
        LOGGER.info("Years of work: " + experienceYears);
    }

    @Override
    public void showSchedule() {
        System.out.println("Monday: " + getDayType(schedule[0]) + "\n" +
                "Tuesday: " + getDayType(schedule[1]) + "\n" +
                "Wednesday: " + getDayType(schedule[2]) + "\n" +
                "Thursday: " + getDayType(schedule[3]) + "\n" +
                "Friday: " + getDayType(schedule[4]) + "\n" +
                "Saturday: " + getDayType(schedule[5]) + "\n" +
                "Sunday: " + getDayType(schedule[6]));

    }

    private DayType getDayType(int num) {
        if (num == 1) {
            return DayType.WORKDAY;
        }
        return DayType.DAY_OFF;
    }
}
