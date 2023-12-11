package com.laba.solvd.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceApp {
    private static final Logger LOGGER = LogManager.getLogger(ExecutorServiceApp.class);
    private static final int tasksAmount = 20;

    static {
        System.setProperty("log4j2.configurationFile", "log4j2_2.xml");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);

        for (int i = 0; i < tasksAmount; i++) {
            executorService.execute(() -> {
                try {
                    Connection con = connectionPool.getConnection();
                    LOGGER.info(Thread.currentThread().getName() + " get connection " + con.getId());
                    con.doWork();
                    connectionPool.releaseConnection(con);
                } catch (InterruptedException e) {
                    LOGGER.error(Thread.currentThread().getName() + " " + e.getMessage());
                }
            });
        }
        LOGGER.info(Thread.currentThread().getName() + " shutdown");
        executorService.shutdown();
        LOGGER.info(Thread.currentThread().getName() + " finished");
    }

}
