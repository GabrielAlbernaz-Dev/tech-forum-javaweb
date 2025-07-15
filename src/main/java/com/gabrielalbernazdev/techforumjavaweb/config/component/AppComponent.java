package com.gabrielalbernazdev.techforumjavaweb.config.component;

import com.gabrielalbernazdev.techforumjavaweb.config.module.AppModule;
import com.gabrielalbernazdev.techforumjavaweb.hello.HelloServlet;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(HelloServlet helloServlet);
}
