package com.gabrielalbernazdev.techforumjavaweb.user.infra.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;

import java.sql.Connection;
import java.util.Set;

public class SqliteUserRepository implements UserRepository {

    @Override
    public void save(Connection connection, User user) {

    }

    @Override
    public void saveRoles(Connection connection, Set<Role> roles) {

    }
}
