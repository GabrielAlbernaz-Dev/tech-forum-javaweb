package com.gabrielalbernazdev.techforumjavaweb.util.infra;

import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletUtil {
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String redirectPath) throws IOException {
        if (isHtmxRequest(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("HX-Redirect", redirectPath);
            return;
        }

        response.sendRedirect(redirectPath);
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String forwardPath) throws IOException {
        try {
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            throw new IOException("Failed to forward request to " + forwardPath, e);
        }
    }

    public static void setErrorHeader(HttpServletResponse response, String errorType, String errorMessage) {
        response.setHeader(Constants.ERROR_TYPE_HEADER_KEY, errorType);
        response.setHeader(Constants.ERROR_MESSAGE_HEADER_KEY, errorMessage);
    }

    public static boolean isHtmxRequest(HttpServletRequest request) {
        return "true".equalsIgnoreCase(request.getHeader("HX-Request"));
    }
}
