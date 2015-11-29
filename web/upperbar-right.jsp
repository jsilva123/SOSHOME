<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav navbar-nav navbar-right">
    <li><a href="#">${pageContext.session.getAttribute('username')}</a></li>
    <li>
        <a href="ServletController?cmd=soshome.model.command.UserLogout">Logout</a>
    </li>
</ul>
