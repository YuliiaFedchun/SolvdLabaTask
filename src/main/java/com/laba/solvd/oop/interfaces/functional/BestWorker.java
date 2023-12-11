package com.laba.solvd.oop.interfaces.functional;

import java.util.List;

@FunctionalInterface
public interface BestWorker<T, R> {
    R determine(List<T> value1);
}
