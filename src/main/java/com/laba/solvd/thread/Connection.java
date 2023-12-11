package com.laba.solvd.thread;

import java.util.Random;

public class Connection {
    private final int id;

    public Connection(int id) {
        this.id = id;
    }

    public void doWork() throws InterruptedException {
        Thread.sleep(1000 * (new Random(20).nextInt(10) + 1));
    }

    public int getId() {
        return id;
    }
}
