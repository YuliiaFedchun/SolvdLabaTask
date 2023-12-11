package com.laba.solvd.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureApp {
    private static final Logger LOGGER = LogManager.getLogger(CompletableFutureApp.class);
    private static final int tasksAmount = 20;

    static {
        System.setProperty("log4j2.configurationFile", "log4j2_2.xml");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        CompletableFuture<String>[] completableFutures = new CompletableFuture[tasksAmount];

        for (int i = 0; i < tasksAmount; i++) {
            completableFutures[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    Connection con = connectionPool.getConnection();
                    LOGGER.info(Thread.currentThread().getName() + " get connection " + con.getId());
                    con.doWork();
                    connectionPool.releaseConnection(con);
                } catch (InterruptedException e) {
                    LOGGER.error(Thread.currentThread().getName() + " " + e.getMessage());
                }
                return Thread.currentThread().getName() + " done work.";
            });
        }
        for (CompletableFuture<String> future :
                completableFutures) {
            LOGGER.info(future.get());
        }
        LOGGER.info(Thread.currentThread().getName() + " shutdown");
        executorService.shutdown();
        CompletableFuture.allOf(completableFutures);
        LOGGER.info(Thread.currentThread().getName() + " finished");
    }
}
