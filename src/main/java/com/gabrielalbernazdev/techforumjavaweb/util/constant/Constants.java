package com.gabrielalbernazdev.techforumjavaweb.util.constant;

public class Constants {
    public static final int APP_PORT = Integer.parseInt(System.getenv().getOrDefault("APP_PORT", "9090"));
    public static final String WEBAPP_PATH = System.getenv().getOrDefault("WEBAPP_PATH", "src/main/webapp");
    public static final String DAGGER_COMPONENT_NAME = System.getenv().getOrDefault("DAGGER_COMPONENT_NAME", "dagger.component");
}
