package com.laba.solvd.oop.enums;

public enum WeekDay {
    MON("Monday", 0),
    TUE("Tuesday", 1),
    WED("Wednesday", 2),
    THU("Thursday", 3),
    FRI("Friday", 4),
    SAT("Saturday", 5),
    SUN("Sunday", 6);

    private String displayName;
    private int order;

    WeekDay(String name, int order) {
        this.displayName = name;
        this.order = order;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getOrder() {
        return order;
    }
}
