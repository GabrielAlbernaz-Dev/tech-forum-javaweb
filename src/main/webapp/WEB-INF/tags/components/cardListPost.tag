<%@ tag description="Authentication Form Component" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="true" rtexprvalue="true" %>

<div class="card text-bg-dark border border-secondary border-top-0">
    <div class="card-header bg-primary py-0">
        <h2 class="text-uppercase mb-0">${title}</h2>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item bg-dark text-light">An item</li>
        <li class="list-group-item bg-dark text-light">A second item</li>
        <li class="list-group-item bg-dark text-light">A third item</li>
    </ul>
</div>



