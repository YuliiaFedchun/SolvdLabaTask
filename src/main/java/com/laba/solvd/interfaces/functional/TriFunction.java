package com.laba.solvd.interfaces.functional;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T value1, U value2, V value3);
}
