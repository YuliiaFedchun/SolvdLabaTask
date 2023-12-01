package com.laba.solvd.interfaces;

import java.util.Set;

@FunctionalInterface
public interface Profit<T, R> {
    R calculate(Set<T> value1);
}
