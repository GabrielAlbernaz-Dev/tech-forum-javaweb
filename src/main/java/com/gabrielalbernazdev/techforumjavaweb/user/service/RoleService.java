package com.gabrielalbernazdev.techforumjavaweb.user.service;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.RoleRepository;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Set;

public class RoleService {
    private final DataSource dataSource;
    private final RoleRepository repository;

    @Inject
    public RoleService(DataSource dataSource, RoleRepository repository) {
        this.dataSource = dataSource;
        this.repository = repository;
    }

    public Set<Role> findRolesByNames(Set<Role> roles) throws SQLException {
        return repository.findRolesByNames(dataSource.getConnection(), roles);
    }
}
