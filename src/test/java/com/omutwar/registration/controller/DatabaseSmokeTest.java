package com.omutwar.registration.controller;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseSmokeTest {

//    public static void main(String[] args) throws Exception {
//        Properties props = new Properties();
//        try (InputStream in = DatabaseSmokeTest.class
//                .getClassLoader()
//                .getResourceAsStream("configs/application.properties")) {
//            props.load(in);
//        }
//
//        String url = props.getProperty("db.url");
//        String user = props.getProperty("db.username");
//        String pass = props.getProperty("db.password");
//
//        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
//            System.out.println("Connected to DB: " + url);
//
//            try (Statement st = conn.createStatement()) {
//                st.execute("""
//                    CREATE TABLE IF NOT EXISTS test_items (
//                        id SERIAL PRIMARY KEY,
//                        name TEXT NOT NULL,
//                        created_at TIMESTAMP NOT NULL DEFAULT NOW()
//                    )
//                """);
//                System.out.println("Ensured test_items table exists");
//            }
//
//            try (PreparedStatement ps =
//                         conn.prepareStatement("INSERT INTO test_items (name) VALUES (?)")) {
//                ps.setString(1, "hello-world");
//                ps.executeUpdate();
//                System.out.println("Inserted row");
//            }
//
//            try (Statement st = conn.createStatement();
//                 ResultSet rs = st.executeQuery("SELECT id, name, created_at FROM test_items ORDER BY id")) {
//                System.out.println("Rows in test_items:");
//                while (rs.next()) {
//                    System.out.printf("  id=%d, name=%s, created_at=%s%n",
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getTimestamp("created_at"));
//                }
//            }
//
//            try (Statement st = conn.createStatement()) {
//                st.execute("DELETE FROM test_items");
//                System.out.println("Deleted all rows from test_items");
//            }
//        }
//    }
}