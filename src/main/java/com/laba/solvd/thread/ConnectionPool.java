package com.laba.solvd.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private volatile static ConnectionPool connectionPoolInstance;
    private final BlockingQueue<Connection> connections;

    private ConnectionPool(int size) throws InterruptedException {
        connections = new ArrayBlockingQueue<>(size);
        initializeConnections(size);
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        connections.add(connection);
    }

    public synchronized static ConnectionPool getInstance(int size) throws InterruptedException {
        if (connectionPoolInstance == null) {
            connectionPoolInstance = new ConnectionPool(size);
        }
        return connectionPoolInstance;
    }

    private void initializeConnections(int size) throws InterruptedException {
        for (int i = 0; i < size; i++) {
            Connection con = new Connection(i);
            connections.put(con);
            LOGGER.info("Connection " + con.getId() + " was added to the pool");
        }
    }

}
