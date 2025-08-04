<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="comp" tagdir="/WEB-INF/tags/components" %>

<ui:baseLayout title="Tech Forum">
    <h2 class="text-light">Username: ${sessionScope.user.username}</h2>
</ui:baseLayout>