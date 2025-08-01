package com.gabrielalbernazdev.techforumjavaweb.auth.service;

import com.gabrielalbernazdev.techforumjavaweb.common.exception.DomainException;
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
    private final DataSource dataSource;
    private final UserService userService;

    @Inject
    public AuthService(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }

    public User login(UserRequest userRequest) throws SQLException {
       User loggedUser = userService.findUserByEmail(userRequest.email());
       boolean passwordMatches = loggedUser.getPassword().matches(userRequest.password());

       if(!passwordMatches) {
           throw new DomainException("Invalid credentials. Please check your email and password.");
       }

       return loggedUser;
    }

    public User register(UserRequest userRequest) throws SQLException {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.create("USER"));

        User user = User.create(
            userRequest.username(),
            userRequest.email(),
            userRequest.password(),
            roles
        );

        userService.saveWithRoles(user);

        return user;
    }
}
