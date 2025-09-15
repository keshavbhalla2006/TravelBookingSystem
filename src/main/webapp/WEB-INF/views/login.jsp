<%@ include file="fragments/header.jspf" %>
<c:if test="${param.logout != null}">
    <p style="color: green;">You have been logged out successfully.</p>
</c:if>
<h2>Login</h2>
<form action="${pageContext.request.contextPath}/do-login" method="post">
    <label>Email: <input type="text" name="username"/></label><br/>
    <label>Password: <input type="password" name="password"/></label><br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Login</button>
</form>

<p>Or sign in with Google:</p>
<a href="/oauth2/authorization/google">Sign in with Google</a>
<%@ include file="fragments/footer.jspf" %>
