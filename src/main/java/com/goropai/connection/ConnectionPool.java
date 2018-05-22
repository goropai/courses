package com.goropai.connection;

import com.goropai.manager.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConnectionPool {

    private static List<Connection> availableConnections = new ArrayList<>();
    private static List<Connection> usedConnection = new ArrayList<>();
    private static final int POOL_SIZE = 20;
    private static ConnectionPool instance;
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getSimpleName());


    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            availableConnections.add(createConnection());
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
            LOGGER.info("ConnectionPool has been created");
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection;
        if (availableConnections.isEmpty()) {
            connection = createConnection();
        } else {
            connection = availableConnections.get(availableConnections.size() - 1);
            availableConnections.remove(connection);
        }
        usedConnection.add(connection);
        return connection;
    }

    public synchronized void putBackConnection(Connection connection) {
        if (connection != null) {
            if (usedConnection.remove(connection)) {
                availableConnections.add(connection);
            }
        }
    }

    private static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(Config.getInstance().getProperty(Config.DRIVER));
            connection = DriverManager.getConnection(Config.getInstance().getProperty(Config.URL));
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
        }
        return connection;
    }
}
