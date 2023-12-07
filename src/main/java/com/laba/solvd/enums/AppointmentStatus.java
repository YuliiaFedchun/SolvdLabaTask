package com.laba.solvd.enums;

public enum AppointmentStatus {
    NEW(0),
    PLANED(1),
    DONE(2),
    PAYED(3);

    private int stageNumber;

    AppointmentStatus(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public int getStageNumber() {
        return stageNumber;
    }
}
