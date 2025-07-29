package com.gabrielalbernazdev.techforumjavaweb.user.infra.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;
import com.gabrielalbernazdev.techforumjavaweb.util.database.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SqliteUserRepository implements UserRepository {
    private Logger LOGGER = LoggerFactory.getLogger(SqliteUserRepository.class);

    @Override
    public Set<Role> findRoles(Connection connection, User user) throws SQLException {
        String sql = "SELECT u.id, u.username FROM " + Table.USERS.as("u") +
                    " JOIN " + Table.USER_ROLES.as("ur") + " ON u.id = ur.user_id" +
                    " WHERE u.id = ?";

        Set<Role> roles = new HashSet<>();
        UUID userId = user.getId();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, userId.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String roleId = rs.getString("id");
                    String roleName = rs.getString("name");
                    roles.add(new Role(UUID.fromString(roleId), roleName));
                }
            }
        }

        return roles;
    }

    @Override
    public void save(Connection connection, User user) {

    }

    @Override
    public void saveUserRoles(Connection connection, Set<Role> roles) {
        String sql = "INSERT INTO " + Table.USER_ROLES + " VALUES(?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Role role : roles) {
                pstmt.setString(1, role.getName());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            LOGGER.info(String.format("Successfully saved %s roles with size %s", roles.toString(), roles.size()));
        } catch (SQLException e) {
            LOGGER.error("Error saving roles: {}", e.getMessage());
        }
    }
}
