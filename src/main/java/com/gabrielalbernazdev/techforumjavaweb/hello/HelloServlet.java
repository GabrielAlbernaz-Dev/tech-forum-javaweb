package com.gabrielalbernazdev.techforumjavaweb.hello;

import com.gabrielalbernazdev.techforumjavaweb.config.component.AppComponent;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    @Inject
    HelloService service;

    @Override
    public void init() throws ServletException {
        super.init();
        AppComponent component = (AppComponent) getServletContext().getAttribute(Constants.DAGGER_COMPONENT_NAME);
        component.inject(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(service.sayHello());
    }
}
