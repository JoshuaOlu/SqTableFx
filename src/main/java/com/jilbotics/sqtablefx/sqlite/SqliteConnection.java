package com.jilbotics.sqtablefx.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
    private Connection connection = null;
    File file;

    public SqliteConnection(File file) {
        this.file = file;
        connect(file);
    }

    public boolean connect(File file) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + getFile().getAbsolutePath());
            System.out.println("Connection successful");
            setConnection(conn);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        connect(file);
    }
}
