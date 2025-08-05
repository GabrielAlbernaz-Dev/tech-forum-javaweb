package com.gabrielalbernazdev.techforumjavaweb.auth.filter;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

public class CSRFFilter implements Filter {
    private final Set<String> METHODS_TO_CHECK_CSRF = Set.of("POST", "PUT", "DELETE");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final User loggedUser = (User) httpRequest.getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        final boolean isAuthenticated = loggedUser != null;

        if(isValidMethodToCheckCsrf(httpRequest) && isAuthenticated) {
            String sessionCsrfToken = (String) httpRequest.getSession().getAttribute(Constants.CSRF_TOKEN_ATTRIBUTE);
            String requestCsrfToken = httpRequest.getParameter(Constants.CSRF_TOKEN_ATTRIBUTE);

            if (sessionCsrfToken == null || !sessionCsrfToken.equals(requestCsrfToken)) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidMethodToCheckCsrf(HttpServletRequest request) {
        final String method = request.getMethod();
        return METHODS_TO_CHECK_CSRF.contains(method);
    }
}
