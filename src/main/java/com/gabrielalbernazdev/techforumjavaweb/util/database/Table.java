package com.gabrielalbernazdev.techforumjavaweb.util.database;

public enum Table {
    USERS("users"),
    ROLES("roles"),
    USER_ROLES("user_roles");

    private final String name;

    Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String as(String alias) {
        return this.name + " " + alias;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
