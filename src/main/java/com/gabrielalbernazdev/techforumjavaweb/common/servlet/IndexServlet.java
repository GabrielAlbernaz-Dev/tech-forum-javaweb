package com.gabrielalbernazdev.techforumjavaweb.common.servlet;

import com.gabrielalbernazdev.techforumjavaweb.util.infra.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletUtil.forward(req, resp, "/index.jsp");
    }
}
