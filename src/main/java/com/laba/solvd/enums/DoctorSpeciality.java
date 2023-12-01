package com.laba.solvd.enums;

public enum DoctorSpeciality {
    ORTHOPAEDIST("orthopaedist"),
    DENTIST("dentist"),
    THERAPIST("therapist"),
    NEUROLOGIST("neurologist"),
    DERMATOLOGIST("dermatologist"),
    OPHTHALMOLOGIST("ophthalmologist");

    private String displayName;

    DoctorSpeciality(String name) {
        this.displayName = name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
