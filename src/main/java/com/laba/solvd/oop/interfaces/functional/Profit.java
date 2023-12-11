package com.laba.solvd.oop.interfaces.functional;

import java.util.Set;

@FunctionalInterface
public interface Profit<T, R> {
    R calculate(Set<T> value1);
}
