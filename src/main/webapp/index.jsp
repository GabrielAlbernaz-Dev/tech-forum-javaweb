<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="comp" tagdir="/WEB-INF/tags/components" %>

<ui:baseLayout title="Tech Forum">
    <jsp:body>
        <div id="auth-content" class="min-vh-50 d-flex align-items-center justify-content-center">
            <jsp:include page="/WEB-INF/views/login.jsp" />
        </div>
    </jsp:body>
</ui:baseLayout>