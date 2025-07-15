package com.gabrielalbernazdev.techforumjavaweb.config.module;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.File;

@Module
public class DataSourceModule {
    @Provides
    @Singleton
    public DataSource provideDataSource() {
        HikariConfig config = new HikariConfig();
        String dbFilePath = String.format("%s/database.db", new File("src/main/resources").getAbsolutePath());

        config.setJdbcUrl("jdbc:sqlite:" + dbFilePath);

        config.setDriverClassName("org.sqlite.JDBC");

        config.setMaximumPoolSize(5);
        config.setMinimumIdle(1);
        config.setAutoCommit(true);
        config.setPoolName("SQLiteHikariCP");

        return new HikariDataSource(config);
    }
}
