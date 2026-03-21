//package com.omutwar.registration.config;
//
//import javax.sql.DataSource;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//public class DataSourceProvider {
//
//    private static final HikariDataSource DATA_SOURCE;
//
//    static {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(DatabaseConfig.get("db.url"));
//        config.setUsername(DatabaseConfig.get("db.username"));
//        config.setPassword(DatabaseConfig.get("db.password"));
//
//        config.setMaximumPoolSize(DatabaseConfig.getInt("db.pool.size", 30));
//        config.setConnectionTimeout(DatabaseConfig.getInt("db.pool.connectionTimeoutMs", 30000));
//        config.setMaxLifetime(DatabaseConfig.getInt("db.pool.maxLifetimeMs", 1800000));
//
//        config.setAutoCommit(false);
//
//        DATA_SOURCE = new HikariDataSource(config);
//    }
//
//    public static DataSource get() {
//        return DATA_SOURCE;
//    }
//}
