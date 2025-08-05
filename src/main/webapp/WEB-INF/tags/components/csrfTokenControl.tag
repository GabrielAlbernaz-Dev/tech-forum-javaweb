<%@ tag description="Navbar Component" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.csrfToken}">
    <input type="hidden" name="${applicationScope.CSRF_TOKEN_ATTRIBUTE}" value="${sessionScope.csrfToken}" />
</c:if>


