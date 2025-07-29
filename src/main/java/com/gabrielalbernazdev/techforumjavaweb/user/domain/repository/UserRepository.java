package com.gabrielalbernazdev.techforumjavaweb.user.domain.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public interface UserRepository {
    Set<Role> findRoles(Connection connection, User user) throws SQLException;
    void save(Connection connection, User user);
    void saveUserRoles(Connection connection, Set<Role> roles);
}
