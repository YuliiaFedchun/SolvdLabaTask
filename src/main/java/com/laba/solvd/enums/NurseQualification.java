package com.laba.solvd.enums;

public enum NurseQualification {
    THIRD(0),
    SECOND(5),
    FIRST(7),
    HIGHER(10);

    private int yearsOfExperience;

    NurseQualification(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}
