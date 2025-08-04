package com.gabrielalbernazdev.techforumjavaweb.auth.servlet;

import com.gabrielalbernazdev.techforumjavaweb.auth.service.AuthService;
import com.gabrielalbernazdev.techforumjavaweb.common.domain.vo.ErrorType;
import com.gabrielalbernazdev.techforumjavaweb.common.exception.DomainException;
import com.gabrielalbernazdev.techforumjavaweb.config.component.AppComponent;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;
import com.gabrielalbernazdev.techforumjavaweb.user.dto.UserRequest;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Constants;
import com.gabrielalbernazdev.techforumjavaweb.util.constant.Routes;
import com.gabrielalbernazdev.techforumjavaweb.util.infra.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.inject.Inject;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Inject
    AuthService authService;

    @Override
    public void init() throws ServletException {
        super.init();
        AppComponent component = (AppComponent) getServletContext().getAttribute(Constants.DAGGER_COMPONENT_NAME);
        component.inject(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletUtil.forward(req, resp, Constants.VIEWS_PATH + "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserRequest userRequest = new UserRequest(
            req.getParameter("username"),
            req.getParameter("email"),
            req.getParameter("password")
        );

        try {
            User registeredUser = authService.register(userRequest);
            req.getSession().setAttribute(Constants.USER_SESSION_ATTRIBUTE, registeredUser);
            ServletUtil.redirect(req, resp, Routes.INDEX);
        } catch (DomainException de) {
            resp.setStatus(HttpServletResponse.SC_UNPROCESSABLE_CONTENT);
            ServletUtil.setErrorHeader(resp, ErrorType.VALIDATION.toString(), de.getMessage());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ServletUtil.setErrorHeader(resp, ErrorType.GENERIC.toString(), e.getMessage());
        }
    }
}
