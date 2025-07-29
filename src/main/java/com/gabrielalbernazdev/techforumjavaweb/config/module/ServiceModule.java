package com.gabrielalbernazdev.techforumjavaweb.config.module;

import com.gabrielalbernazdev.techforumjavaweb.auth.service.AuthService;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;
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
    public UserService provideUserService(DataSource dataSource, UserRepository repository) {
        return new UserService(dataSource, repository);
    }
}
