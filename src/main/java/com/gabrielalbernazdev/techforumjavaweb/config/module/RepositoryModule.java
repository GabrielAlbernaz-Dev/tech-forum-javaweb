package com.gabrielalbernazdev.techforumjavaweb.config.module;

import com.gabrielalbernazdev.techforumjavaweb.post.domain.repository.PostRepository;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.RoleRepository;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.UserRepository;
import com.gabrielalbernazdev.techforumjavaweb.post.infra.repository.SqlitePostRepository;
import com.gabrielalbernazdev.techforumjavaweb.user.infra.repository.SqliteRoleRepository;
import com.gabrielalbernazdev.techforumjavaweb.user.infra.repository.SqliteUserRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    public UserRepository provideUserRepository() {
        return new SqliteUserRepository();
    }

    @Provides
    @Singleton
    public RoleRepository provideRoleRepository() {
        return new SqliteRoleRepository();
    }

    @Provides
    @Singleton
    public PostRepository providePostRepository() {
        return new SqlitePostRepository();
    }
}
