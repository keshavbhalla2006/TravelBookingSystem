<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../fragments/header.jspf" %>
<h2>Users</h2>
<a href="/admin/users/create">+ New User</a>
<table border="1" cellpadding="6" cellspacing="0">
    <thead>
    <tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Actions</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
            <td>${u.role}</td>
            <td>
                <a href="/admin/users/${u.id}/edit">Edit</a>
                <form action="/admin/users/${u.id}/delete" method="post" style="display:inline">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" onclick="return confirm('Delete this user?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="../fragments/footer.jspf" %>
