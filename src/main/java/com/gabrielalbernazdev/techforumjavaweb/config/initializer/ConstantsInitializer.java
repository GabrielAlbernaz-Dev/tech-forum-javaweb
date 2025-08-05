package com.gabrielalbernazdev.techforumjavaweb.config.initializer;

import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Routes;
import jakarta.servlet.ServletContextListener;

import java.util.HashMap;
import java.util.Map;

public class ConstantsInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
        jakarta.servlet.ServletContext context = sce.getServletContext();
        context.setAttribute("USER_SESSION_ATTRIBUTE", Constants.USER_SESSION_ATTRIBUTE);
        context.setAttribute("CSRF_TOKEN_ATTRIBUTE", Constants.CSRF_TOKEN_ATTRIBUTE);
        context.setAttribute("ROUTES", getRoutes());
    }

    private Map<String, String> getRoutes() {
        Map<String, String> routes = new HashMap<>();
        routes.put("index", Routes.INDEX);
        routes.put("login", Routes.LOGIN);
        routes.put("register", Routes.REGISTER);
        routes.put("logout", Routes.LOGOUT);
        return routes;
    }
}
