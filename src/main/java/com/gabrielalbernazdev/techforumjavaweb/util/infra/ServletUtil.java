package com.gabrielalbernazdev.techforumjavaweb.util.infra;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletUtil {
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String redirectPath) throws IOException {
        if ("true".equals(request.getHeader("HX-Request"))) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("HX-Redirect", redirectPath);
        } else {
            response.sendRedirect(redirectPath);
        }
    }
}
