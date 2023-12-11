package com.laba.solvd.oop.interfaces;

import com.laba.solvd.oop.enums.WeekDay;

public interface Department {
    boolean isOpened(WeekDay weekDay, int hour);

    String getName();
}
