package com.gabrielalbernazdev.techforumjavaweb.user.infra.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;
import com.gabrielalbernazdev.techforumjavaweb.util.database.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SqliteUserRepository implements UserRepository {
    @Override
    public Optional<User> findById(Connection connection, UUID id) throws SQLException {
        String sql = "SELECT id, username, email, password, created_at FROM " + Table.USERS + " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            return extractUser(connection, stmt);
        }
    }

    @Override
    public Optional<User> findByEmail(Connection connection, String email) throws SQLException {
        String sql = "SELECT id, username, email, password, created_at FROM " + Table.USERS + " WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            return extractUser(connection, stmt);
        }
    }

    @Override
    public Set<Role> findRolesByUserId(Connection connection, UUID userId) throws SQLException {
        String sql = "SELECT u.id, r.name FROM " + Table.USERS.as("u") +
                    " JOIN " + Table.USER_ROLES.as("ur") + " ON u.id = ur.user_id" +
                    " JOIN " + Table.ROLES.as("r") + " ON ur.role_id = r.id" +
                    " WHERE u.id = ?";

        Set<Role> roles = new HashSet<>();

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
    public void save(Connection connection, User user) throws SQLException {
        Optional<User> existedUser = findById(connection, user.getId());

        if (existedUser.isPresent()) {
            String updateSql = "UPDATE " + Table.USERS +
                             " SET username = ?, email = ?, password = ? WHERE id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(updateSql)) {
                pstmt.setString(1, user.getUsername().getValue());
                pstmt.setString(2, user.getEmail().getValue());
                pstmt.setString(3, user.getPassword().getValue());
                pstmt.setString(4, user.getId().toString());
                pstmt.executeUpdate();
            }
        } else {
            String insertSql = "INSERT INTO " + Table.USERS +
                             " (id, username, email, password, created_at) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                pstmt.setString(1, user.getId().toString());
                pstmt.setString(2, user.getUsername().getValue());
                pstmt.setString(3, user.getEmail().getValue());
                pstmt.setString(4, user.getPassword().getValue());
                pstmt.setString(5, user.getCreatedAt().toString());
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public void saveUserRoles(Connection connection, User user) throws SQLException {
        String sql = "INSERT INTO " + Table.USER_ROLES + "(user_id, role_id) VALUES(?, ?)";
        Set<Role> roles = user.getRoles();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Role role : roles) {
                pstmt.setString(1, user.getId().toString());
                pstmt.setString(2, role.getId().toString());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    private Optional<User> extractUser(Connection connection, PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            if (!rs.next()) {
                return Optional.empty();
            }

            UUID userId = UUID.fromString(rs.getString("id"));
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String createdAt = rs.getString("created_at");
            LocalDateTime createdAtParsed = LocalDateTime.parse(createdAt);

            Set<Role> roles = findRolesByUserId(connection, userId);

            User user = User.reconstruct(
                    userId,
                    username,
                    email,
                    password,
                    createdAtParsed,
                    roles,
                    new HashSet<>(),
                    new HashSet<>()
            );

            return Optional.of(user);
        }
    }
}
