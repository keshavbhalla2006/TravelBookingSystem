<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../fragments/header.jspf" %>
<h2>${user.id == null ? "Create User" : "Edit User"}</h2>
<form action="/admin/users/save" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <label>Name: <input type="text" name="name" value="${user.name}"/></label><br/>
    <label>Email: <input type="email" name="email" value="${user.email}"/></label><br/>
    <label>Password: <input type="password" name="password" value=""/></label>
    <small><c:if test="${user.id != null}">(leave blank to keep existing)</c:if></small><br/>
    <label>Role:
        <select name="rol3e">
            <c:forEach items="${roles}" var="r">
                <option value="${r}" ${r == user.role ? "selected" : ""}>${r}</option>
            </c:forEach>
        </select>
    </label><br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Save</button>
</form>
<%@ include file="../fragments/footer.jspf" %>
