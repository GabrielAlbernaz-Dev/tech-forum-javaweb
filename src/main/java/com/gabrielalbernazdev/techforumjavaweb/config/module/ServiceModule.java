package com.gabrielalbernazdev.techforumjavaweb.config.module;

import com.gabrielalbernazdev.techforumjavaweb.hello.HelloService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ServiceModule {
    @Provides
    @Singleton
    public HelloService provideHelloService() {
        return new HelloService();
    }
}
