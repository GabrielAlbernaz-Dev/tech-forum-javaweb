package com.gabrielalbernazdev.techforumjavaweb.user.domain.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public interface RoleRepository {
    Set<Role> findRolesByNames(Connection connection, Set<Role> roles) throws SQLException;
}
