package com.gabrielalbernazdev.techforumjavaweb.auth.servlet;

import com.gabrielalbernazdev.techforumjavaweb.util.constant.Routes;
import com.gabrielalbernazdev.techforumjavaweb.util.infra.ServletUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        ServletUtil.redirect(req, resp, Routes.LOGIN);
    }
}
