package com.gabrielalbernazdev.techforumjavaweb.config.module;

import dagger.Module;

@Module(includes = {
    DataSourceModule.class,
    ServiceModule.class,
    RepositoryModule.class
})
public abstract class AppModule {

}
