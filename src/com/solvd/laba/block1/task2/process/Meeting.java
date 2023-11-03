package com.solvd.laba.block1.task2.process;

import com.solvd.laba.block1.task2.model.Person;

public abstract class Meeting {
    Person person1;
    Person person2;
    String data;
    String time;

    public abstract Meeting plan();
    public abstract boolean delete();
}
