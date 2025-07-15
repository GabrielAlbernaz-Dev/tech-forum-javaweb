package com.gabrielalbernazdev.techforumjavaweb.config.initializer;


import com.gabrielalbernazdev.techforumjavaweb.config.component.AppComponent;
import com.gabrielalbernazdev.techforumjavaweb.config.component.DaggerAppComponent;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class DaggerInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        AppComponent component = DaggerAppComponent.create();
        context.setAttribute(Constants.DAGGER_COMPONENT_NAME, component);
    }
}
