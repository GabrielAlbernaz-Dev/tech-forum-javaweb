package com.gabrielalbernazdev.techforumjavaweb.auth.filter;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import com.gabrielalbernazdev.techforumjavaweb.util.infra.ServletUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AuthFilter implements Filter {
    private static final String REDIRECT_PATH = "/auth/login";
    private static final List<String> ALLOWED_PATHS = List.of(
            "/auth/",
            "/resources/"
    );
    private static final List<String> ALLOWED_EXTENSIONS = List.of(
            ".css",
            ".js",
            ".png",
            ".jpg",
            ".gif",
            ".svg",
            ".ico",
            ".woff",
            ".woff2"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String path = httpRequest.getServletPath();
        final User loggedUser = (User) httpRequest.getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        final boolean isAuthenticated = loggedUser != null;
        final boolean isAuthRedirect = "true".equalsIgnoreCase(httpRequest.getHeader("X-Auth-Redirect"));

        if (isPathAllowed(path) || isStaticResource(path) || isAuthRedirect) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!isAuthenticated) {
            ServletUtil.redirect(httpRequest, httpResponse, httpRequest.getContextPath() + REDIRECT_PATH);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPathAllowed(String path) {
        return ALLOWED_PATHS.stream().anyMatch(path::startsWith);
    }

    private boolean isStaticResource(String path) {
        return ALLOWED_EXTENSIONS.stream().anyMatch(path::startsWith);
    }
}
