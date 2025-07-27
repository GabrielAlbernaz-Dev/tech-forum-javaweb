package com.gabrielalbernazdev.techforumjavaweb.config.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UnitOfWork implements AutoCloseable {
    private final Connection conn;
    private boolean completed = false;

    public UnitOfWork(DataSource dataSource) throws SQLException {
        this.conn = dataSource.getConnection();
        this.conn.setAutoCommit(false);
    }

    public Connection getConnection() {
        return conn;
    }

    public void commit() throws SQLException {
        conn.commit();
        completed = true;
    }

    public void rollback() throws SQLException {
        conn.rollback();
        completed = true;
    }

    @Override
    public void close() throws SQLException {
        if (!completed) {
            rollback();
        }
        conn.close();
    }
}
