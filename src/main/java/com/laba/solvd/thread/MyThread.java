package com.laba.solvd.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(MyThread.class);

    @Override
    public void run() {
        LOGGER.info("My thread works.");
    }

}
