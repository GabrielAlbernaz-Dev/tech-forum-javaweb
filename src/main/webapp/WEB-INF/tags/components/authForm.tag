<%@ tag description="Authentication Form Component" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comp" tagdir="/WEB-INF/tags/components" %>
<%@ attribute name="title" required="true" rtexprvalue="true" %>
<%@ attribute name="externalLinkType" required="true" rtexprvalue="true" %>
<%@ attribute name="action" required="true" rtexprvalue="true" %>
<%@ attribute name="fields" required="true" type="java.util.List" rtexprvalue="true" %>
<%@ attribute name="submitLabel" required="true" rtexprvalue="true" %>

<section id="auth-content" class="min-vh-50 d-flex align-items-center justify-content-center">
    <div class="w-100 d-flex justify-content-center align-items-center">
        <div id="form-border" class="border border-white rounded p-3">
            <h1 class="text-light py-3 text-center">${title}</h1>
            <div class="text-right">
                <p class="text-light mb-0">
                    <c:choose>
                        <c:when test="${externalLinkType eq 'register'}">
                            Don't have an account?
                            <a href="<c:out value="${applicationScope.ROUTES['register']}" />"
                               class="text-primary">
                                Register here
                            </a>
                        </c:when>
                        <c:when test="${externalLinkType eq 'login'}">
                            Already have an account?
                            <a href="<c:out value="${applicationScope.ROUTES['login']}" />"
                               class="text-primary">
                                Login here
                            </a>
                        </c:when>
                    </c:choose>
                </p>
            </div>
            <form class="py-2" hx-post="${action}" hx-validate="true">
                <c:forEach items="${fields}" var="field">
                    <div class="form-group py-2">
                        <label for="${field.id}" class="form-label text-light">${field.label}</label>
                        <input
                            type="${field.type}"
                            id="${field.id}"
                            name="${field.name}"
                            class="form-control"
                            <c:if test="${not empty field.value}">value="${field.value}"</c:if>
                            <c:if test="${field.required}">required</c:if>
                        />
                    </div>
                </c:forEach>
                <button type="submit" class="btn btn-primary text-light mt-3 w-100">${submitLabel}</button>
            </form>
        </div>
    </div>
</section>
