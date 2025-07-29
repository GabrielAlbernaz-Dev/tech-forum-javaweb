package com.gabrielalbernazdev.techforumjavaweb.user.service;

import com.gabrielalbernazdev.techforumjavaweb.config.database.UnitOfWork;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Set;

public class UserService {
    private final UserRepository repository;
    private final DataSource dataSource;

    public UserService(DataSource dataSource, UserRepository repository) {
        this.dataSource = dataSource;
        this.repository = repository;
    }

    public Set<Role> findRoles(User user) throws SQLException {
        return repository.findRoles(dataSource.getConnection(), user);
    }

    public void save(User user) throws SQLException {
        repository.save(dataSource.getConnection(), user);
    }

    public void saveUserRoles(Set<Role> roles) throws SQLException {
        repository.saveUserRoles(dataSource.getConnection(), roles);
    }

    public void saveWithRoles(User user) throws SQLException {
        Set<Role> roles = findRoles(user);

        try(final UnitOfWork uow = new UnitOfWork(dataSource)) {
            try {
                save(user);
                saveUserRoles(roles);
                uow.commit();
            } catch (SQLException e) {
                uow.rollback();
            }
        }
    }
}
