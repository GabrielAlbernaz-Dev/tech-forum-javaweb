package com.gabrielalbernazdev.techforumjavaweb.config.module;

import com.gabrielalbernazdev.techforumjavaweb.auth.service.AuthService;
import com.gabrielalbernazdev.techforumjavaweb.post.domain.repository.PostRepository;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.RoleRepository;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;
import com.gabrielalbernazdev.techforumjavaweb.post.service.PostService;
import com.gabrielalbernazdev.techforumjavaweb.user.service.RoleService;
import com.gabrielalbernazdev.techforumjavaweb.user.service.UserService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import javax.sql.DataSource;

@Module
public class ServiceModule {
    @Provides
    @Singleton
    public AuthService provideAuthService(DataSource dataSource, UserService userService) {
        return new AuthService(dataSource, userService);
    }

    @Provides
    @Singleton
    public RoleService provideRoleService(DataSource dataSource, RoleRepository repository) {
        return new RoleService(dataSource, repository);
    }

    @Provides
    @Singleton
    public UserService provideUserService(DataSource dataSource, UserRepository repository, RoleService roleService) {
        return new UserService(dataSource, repository, roleService);
    }

    @Provides
    @Singleton
    public PostService providePostService(DataSource dataSource, PostRepository repository) {
        return new PostService(dataSource, repository);
    }
}
