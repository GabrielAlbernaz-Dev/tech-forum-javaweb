<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="comp" tagdir="/WEB-INF/tags/components" %>

<ui:baseLayout title="Tech Forum">
    <div class="d-flex flex-column flex-md-row justify-content-between" style="gap: 3.5rem">
        <h1 class="text-light">TechForum Forums</h1>
        <comp:cardListPost title="Latest Posts" />
    </div>
</ui:baseLayout>