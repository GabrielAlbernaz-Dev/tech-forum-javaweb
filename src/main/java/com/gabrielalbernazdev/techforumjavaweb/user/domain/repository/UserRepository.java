package com.gabrielalbernazdev.techforumjavaweb.user.domain.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;

import java.sql.Connection;
import java.util.Set;

public interface UserRepository {
    void save(Connection connection, User user);
    void saveRoles(Connection connection, Set<Role> roles);
}
