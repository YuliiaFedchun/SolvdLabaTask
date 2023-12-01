package com.laba.solvd.interfaces;

import com.laba.solvd.enums.WeekDay;

public interface Department {
    boolean isOpened(WeekDay weekDay, int hour);

    String getName();
}
