<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="comp" tagdir="/WEB-INF/tags/components" %>
<%@ page import="com.gabrielalbernazdev.techforumjavaweb.web.viewobject.FormField" %>
<%@ page import="java.util.*" %>

<%
    List<FormField> fields = new ArrayList<>();
    fields.add(new FormField("Email", "email", "email", "email", true));
    fields.add(new FormField("Password", "password", "password", "password", true));
    request.setAttribute("formFields", fields);
%>

<ui:baseLayout title="Login - Tech Forum">
    <jsp:body>
        <comp:authForm
            title="Login"
            action="${pageContext.request.contextPath}/auth/login"
            fields="${formFields}"
            submitLabel="Sign in"
            externalLinkType="register"
        />
    </jsp:body>
</ui:baseLayout>