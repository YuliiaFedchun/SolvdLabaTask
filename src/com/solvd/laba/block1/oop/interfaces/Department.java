package com.solvd.laba.block1.oop.interfaces;

import com.solvd.laba.block1.oop.enums.WeekDay;

public interface Department {
    boolean isOpened(WeekDay weekDay, int hour);
}
