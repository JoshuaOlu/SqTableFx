package com.jilbotics.sqtablefx.sqlite;

import java.sql.*;
import java.util.ArrayList;

public class Test {
    public static void createSubFacultyTables(Connection conn) throws SQLException {
        String queryDelete = "DELETE from Setting_Faculty";
        PreparedStatement pst;
        pst = conn.prepareStatement(queryDelete);
        pst.execute();
        pst.close();

        String selectTableQuery = "SELECT name FROM sqlite_master WHERE type = 'table' AND name LIKE 'Setting_Faculty_%'";
        ResultSet rs = null;

        pst = conn.prepareStatement(selectTableQuery);
        rs = pst.executeQuery();
        ArrayList<String> tables = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            tables.add(name);
        }
        pst.close();
        rs.close();

        for (String name : tables) {
            String tableDeleteQuery = "DROP TABLE IF EXISTS " + name;
            Statement stmt = conn.createStatement();
            stmt.execute(tableDeleteQuery);
            stmt.close();
        }
    }

    public static ArrayList<String> getAllTables(Connection conn) throws SQLException {
        String selectTableQuery = "SELECT name FROM sqlite_master WHERE type = 'table'";
        ResultSet rs = null;

        PreparedStatement pst = conn.prepareStatement(selectTableQuery);
        rs = pst.executeQuery();
        ArrayList<String> tables = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            tables.add(name);
        }
        pst.close();
        rs.close();

        return tables;
    }

    public static ArrayList<String> getAllColumns(Connection conn, String tableName) throws SQLException {
        String selectTableQuery = "PRAGMA table_info('"+ tableName +"')";
        ResultSet rs = null;

        PreparedStatement pst = conn.prepareStatement(selectTableQuery);
        rs = pst.executeQuery();
        ArrayList<String> tables = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            tables.add(name);
        }
        pst.close();
        rs.close();

        return tables;
    }

    public static ArrayList<ArrayList<String>> getAllRows(Connection conn, String tableName) throws SQLException {
        ArrayList<String> columnNames = getAllColumns(conn, tableName);

        String selectTableQuery = "SELECT * FROM '"+ tableName +"'";
        ResultSet rs = null;

        PreparedStatement pst = conn.prepareStatement(selectTableQuery);
        rs = pst.executeQuery();
        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        while (rs.next()) {
            ArrayList<String> singleRow = new ArrayList<>();
            for (String columnName : columnNames) {
                String cell = rs.getString(columnName);
                singleRow.add(cell);
            }

            rows.add(singleRow);
        }
        pst.close();
        rs.close();

        return rows;
    }
}
