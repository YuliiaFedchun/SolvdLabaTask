package com.laba.solvd.interfaces;

import java.util.List;

@FunctionalInterface
public interface BestWorker<T, R> {
    R determine(List<T> value1);
}
