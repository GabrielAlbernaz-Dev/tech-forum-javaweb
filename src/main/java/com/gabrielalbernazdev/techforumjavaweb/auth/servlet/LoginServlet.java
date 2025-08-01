package com.gabrielalbernazdev.techforumjavaweb.auth.servlet;

import com.gabrielalbernazdev.techforumjavaweb.auth.service.AuthService;
import com.gabrielalbernazdev.techforumjavaweb.config.component.AppComponent;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.inject.Inject;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Inject
    AuthService authService;

    @Override
    public void init() throws ServletException {
        super.init();
        AppComponent component = (AppComponent) getServletContext().getAttribute(Constants.DAGGER_COMPONENT_NAME);
        component.inject(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
