package com.gabrielalbernazdev.techforumjavaweb.config.module;

import com.gabrielalbernazdev.techforumjavaweb.auth.service.AuthService;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import javax.sql.DataSource;

@Module
public class ServiceModule {
    @Provides
    @Singleton
    public AuthService provideAuthService(UserRepository repository, DataSource dataSource) {
        return new AuthService(dataSource, repository);
    }
}
