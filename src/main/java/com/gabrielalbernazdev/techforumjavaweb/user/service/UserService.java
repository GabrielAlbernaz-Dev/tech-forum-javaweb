package com.gabrielalbernazdev.techforumjavaweb.user.service;

import com.gabrielalbernazdev.techforumjavaweb.config.database.UnitOfWork;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {
    private final DataSource dataSource;
    private final UserRepository repository;
    private final RoleService rolesService;

    public UserService(DataSource dataSource, UserRepository repository, RoleService rolesService) {
        this.dataSource = dataSource;
        this.repository = repository;
        this.rolesService = rolesService;
    }

    public Set<Role> findRolesByUserId(UUID userId) throws SQLException {
        return repository.findRolesByUserId(dataSource.getConnection(), userId);
    }

    public void save(User user) throws SQLException {
        repository.save(dataSource.getConnection(), user);
    }

    public void saveUserRoles(User user) throws SQLException {
        repository.saveUserRoles(dataSource.getConnection(), user);
    }

    public void saveWithRoles(User user) throws SQLException {
        Set<Role> roles = rolesService.findRolesByNames(user.getRoles());

        User userToSave = User.reconstruct(
                user.getId(),
                user.getUsername().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getCreatedAt(),
                roles,
                user.getFollowers().collect(Collectors.toSet()),
                user.getFollowing()
        );

        try(final UnitOfWork uow = new UnitOfWork(dataSource)) {
            try {
                save(user);
                saveUserRoles(userToSave);
                uow.commit();
            } catch (SQLException e) {
                uow.rollback();
                throw new SQLException("Error saving user with roles", e);
            }
        }
    }
}
