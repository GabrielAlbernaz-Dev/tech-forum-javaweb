package com.gabrielalbernazdev.techforumjavaweb;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);
        tomcat.getConnector();

        File baseDir = new File("src/main/webapp");
        Context context = tomcat.addWebapp("", baseDir.getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
