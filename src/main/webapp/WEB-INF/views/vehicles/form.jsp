<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../fragments/header.jspf" %>

<style>
/* --- Form Container --- */
form {
    max-width: 600px;
    margin: 50px auto;
    padding: 30px 40px;
    background: linear-gradient(145deg, #f8f9fa, #e9ecef);
    border-radius: 15px;
    box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* --- Heading --- */
h2 {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 25px;
    color: #343a40;
}

/* --- Labels & Inputs --- */
label {
    display: block;
    margin-bottom: 15px;
    font-weight: 500;
    color: #495057;
}

input[type="text"],
input[type="number"],
select {
    width: 100%;
    padding: 10px 12px;
    margin-top: 5px;
    border: 1px solid #ced4da;
    border-radius: 8px;
    font-size: 1rem;
    transition: all 0.3s ease;
}

input[type="text"]:focus,
input[type="number"]:focus,
select:focus {
    border-color: #0d6efd;
    box-shadow: 0 0 5px rgba(13,110,253,0.3);
    outline: none;
}

/* --- Submit Button --- */
button {
    display: block;
    width: 100%;
    padding: 12px;
    background-color: #0d6efd;
    color: #fff;
    font-size: 1.1rem;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    margin-top: 20px;
    transition: all 0.3s ease;
}

button:hover {
    background-color: #0b5ed7;
    transform: translateY(-2px);
    box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}
</style>

<h2>${vehicle.id == null ? "Create Vehicle" : "Edit Vehicle"}</h2>

<form action="/vehicles/save" method="post">
    <input type="hidden" name="id" value="${vehicle.id}"/>

    <label>Type:
        <select name="type">
            <c:forEach items="${types}" var="t">
                <option value="${t}" ${t == vehicle.type ? "selected" : ""}>${t}</option>
            </c:forEach>
        </select>
    </label>

    <label>Number: <input type="text" name="number" value="${vehicle.number}"/></label>

    <label>Capacity: <input type="number" name="capacity" value="${vehicle.capacity}"/></label>

    <label>Description: <input type="text" name="description" value="${vehicle.description}"/></label>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <button type="submit">Save</button>
</form>

<%@ include file="../fragments/footer.jspf" %>
