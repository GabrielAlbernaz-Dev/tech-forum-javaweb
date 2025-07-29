package com.gabrielalbernazdev.techforumjavaweb.user.domain.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(Connection connection, UUID id) throws SQLException;
    Set<Role> findRolesByUserId(Connection connection, UUID userId) throws SQLException;
    void save(Connection connection, User user) throws SQLException;
    void saveUserRoles(Connection connection, User user) throws SQLException;
}
