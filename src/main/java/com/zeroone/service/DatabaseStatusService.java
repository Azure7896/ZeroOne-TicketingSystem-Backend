package com.zeroone.service;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseStatusService {

    private final DataSource dataSource;

    public DatabaseStatusService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isDatabaseConnected() {
        try (Connection connection = dataSource.getConnection()) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
