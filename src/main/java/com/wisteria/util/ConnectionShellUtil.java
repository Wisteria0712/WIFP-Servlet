package com.wisteria.util;

import java.sql.Connection;

public class ConnectionShellUtil {
    private static final ThreadLocal<Connection> connectionShellUtil = new ThreadLocal<>();

    private ConnectionShellUtil() {
    }

    public static void setConnection(Connection connection) {
        connectionShellUtil.set(connection);
    }

    public static Connection getConnection() {
        return (Connection) connectionShellUtil.get();
    }

    public static void removeConnection() {
        connectionShellUtil.remove();
    }
}
