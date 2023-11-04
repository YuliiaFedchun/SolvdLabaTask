package com.solvd.laba.block1.oop.model;

import com.solvd.laba.block1.oop.enums.WeekDay;

import java.util.Arrays;
import java.util.Objects;

public class Doctor extends Person {

    private String speciality;
    private int[][] schedule;
    private int consultationCost;

    public Doctor(String firstName, String lastName, int age, String phoneNumber, String address,
                  String speciality, int consultationCost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.speciality = speciality;
        this.consultationCost = consultationCost;
        schedule = new int[7][10];
    }

    public int getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(int consultationCost) {
        this.consultationCost = consultationCost;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int[][] getSchedule() {
        return schedule;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setSchedule(int[][] schedule) {
        this.schedule = schedule;
    }

    @Override
    public void showContactInfo() {
        System.out.println("Address: " + this.address + "\n"
                + "Phone number: " + this.phoneNumber);
    }

    public boolean isTimeSlotFree(WeekDay weekDay, int timeSlot) {
        if (timeSlot >= 20) {
            System.out.println("Incorrect time slot.");
            return false;
        }

        switch (weekDay) {
            case MON:
                if (schedule[0][timeSlot] == 0) {
                    return true;
                }
                break;
            case TUE:
                if (schedule[1][timeSlot] == 0) {
                    return true;
                }
                break;
            case WED:
                if (schedule[2][timeSlot] == 0) {
                    return true;
                }
                break;
            case THU:
                if (schedule[3][timeSlot] == 0) {
                    return true;
                }
                break;
            case FRI:
                if (schedule[4][timeSlot] == 0) {
                    return true;
                }
                break;
            case SAT:
                if (schedule[5][timeSlot] == 0) {
                    return true;
                }
                break;
            case SUN:
                if (schedule[6][timeSlot] == 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean chooseTimeSlot(WeekDay weekDay, int timeSlot) {
        if (isTimeSlotFree(weekDay, timeSlot)) {
            switch (weekDay) {
                case MON:
                    schedule[0][timeSlot] = 1;
                    return true;
                case TUE:
                    schedule[1][timeSlot] = 1;
                    return true;
                case WED:
                    schedule[2][timeSlot] = 1;
                    return true;
                case THU:
                    schedule[3][timeSlot] = 1;
                    return true;
                case FRI:
                    schedule[4][timeSlot] = 1;
                    return true;
                case SAT:
                    schedule[5][timeSlot] = 1;
                    return true;
                case SUN:
                    schedule[6][timeSlot] = 1;
                    return true;
            }

        } else {
            System.out.println("The time slot isn't available.");
            return false;
        }
        return false;
    }

    public void showSchedule() {
        System.out.println("Monday: " + Arrays.toString(schedule[0]) + "\n" +
                "Tuesday: " + Arrays.toString(schedule[1]) + "\n" +
                "Wednesday: " + Arrays.toString(schedule[2]) + "\n" +
                "Thursday: " + Arrays.toString(schedule[3]) + "\n" +
                "Friday: " + Arrays.toString(schedule[4]) + "\n" +
                "Saturday: " + Arrays.toString(schedule[5]) + "\n" +
                "Sunday: " + Arrays.toString(schedule[6]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return firstName.equals(doctor.firstName)
                && lastName.equals(doctor.lastName)
                && speciality.equals(doctor.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, speciality);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", speciality='" + speciality + '\'' +
                ", consultationCost=" + consultationCost +
                '}';
    }
}
