package com.solvd.laba.block1.oop.service;

import com.solvd.laba.block1.oop.enums.WeekDay;

public interface Department {
    boolean isOpened (WeekDay weekDay, int hour);
}
