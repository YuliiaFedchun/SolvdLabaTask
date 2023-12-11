package com.laba.solvd.thread;

import com.laba.solvd.oop.model.payment.BankSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadApp {
    private static final Logger LOGGER = LogManager.getLogger(ThreadApp.class);

    static {
        System.setProperty("log4j2.configurationFile", "log4j2_2.xml");
    }

    public static void main(String[] args) {
        //own MyThread class that extends Thread class
        new MyThread().start();

        //use Thread class and own BankSession class which implements Runnable interface
        new Thread(new BankSession()).start();

        //use Thread class and Lambda expression to implement run method of the Runnable interface
        Runnable task = () -> LOGGER.info("Thread works");
        new Thread(task).start();
    }
}
