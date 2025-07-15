package com.gabrielalbernazdev.techforumjavaweb;

import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Constants.APP_PORT);
        tomcat.getConnector();

        File baseDir = new File(Constants.WEBAPP_PATH);
        Context context = tomcat.addWebapp("", baseDir.getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
