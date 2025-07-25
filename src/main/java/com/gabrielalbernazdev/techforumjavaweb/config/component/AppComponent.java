package com.gabrielalbernazdev.techforumjavaweb.config.component;

import com.gabrielalbernazdev.techforumjavaweb.auth.servlet.LoginServlet;
import com.gabrielalbernazdev.techforumjavaweb.auth.servlet.RegisterServlet;
import com.gabrielalbernazdev.techforumjavaweb.config.module.AppModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(LoginServlet loginServlet);
    void inject(RegisterServlet registerServlet);
}
