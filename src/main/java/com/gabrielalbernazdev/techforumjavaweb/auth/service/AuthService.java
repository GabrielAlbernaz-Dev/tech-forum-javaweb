package com.gabrielalbernazdev.techforumjavaweb.auth.service;

import com.gabrielalbernazdev.techforumjavaweb.config.database.UnitOfWork;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.user.dto.UserRequest;
import com.gabrielalbernazdev.techforumjavaweb.user.service.UserService;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AuthService {
    private final UserService userService;
    private final DataSource dataSource;

    @Inject
    public AuthService(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }

    public String login() {
        return "Login successful";
    }

    public String register(UserRequest userRequest) throws SQLException {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.create("USER"));

        User user = User.create(
            userRequest.username(),
            userRequest.email(),
            userRequest.password(),
            roles
        );

        userService.saveWithRoles(user);

        return "Registration successful";
    }
}
