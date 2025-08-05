package com.gabrielalbernazdev.techforumjavaweb.util.constant;

public class Constants {
    public static final int APP_PORT = Integer.parseInt(System.getenv().getOrDefault("APP_PORT", "9090"));
    public static final String WEBAPP_PATH = System.getenv().getOrDefault("WEBAPP_PATH", "src/main/webapp");
    public static final String VIEWS_PATH = "/WEB-INF/views";
    public static final String DAGGER_COMPONENT_NAME = System.getenv().getOrDefault("DAGGER_COMPONENT_NAME", "dagger.component");
    public static final String USER_SESSION_ATTRIBUTE = "user";
    public static final String CSRF_TOKEN_ATTRIBUTE = "csrfToken";
    public static final String ERROR_MESSAGE_HEADER_KEY = "X-Error-Message";
    public static final String ERROR_TYPE_HEADER_KEY = "X-Error-Type";
}
