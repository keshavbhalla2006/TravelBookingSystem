<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../fragments/header.jspf" %>

<style>
/* --- Page Container --- */
.container {
    max-width: 900px;
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
    margin-bottom: 20px;
    color: #343a40;
}

/* --- New Vehicle Button --- */
.new-btn {
    display: inline-block;
    margin-bottom: 20px;
    padding: 10px 20px;
    background-color: #0d6efd;
    color: #fff;
    text-decoration: none;
    border-radius: 8px;
    font-weight: 500;
    transition: all 0.3s ease;
}

.new-btn:hover {
    background-color: #0b5ed7;
    transform: translateY(-2px);
    box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}

/* --- Table Styling --- */
table {
    width: 100%;
    border-collapse: collapse;
    text-align: left;
    font-size: 1rem;
}

thead {
    background-color: #0d6efd;
    color: #fff;
}

thead th {
    padding: 12px;
    border-radius: 5px 5px 0 0;
}

tbody tr {
    background-color: #ffffff;
    transition: all 0.3s ease;
}

tbody tr:nth-child(even) {
    background-color: #f1f3f5;
}

tbody tr:hover {
    background-color: #e2e6ea;
}

td, th {
    padding: 10px 12px;
}

/* --- Action Buttons --- */
td form, td a {
    display: inline-block;
}

td a.edit-btn {
    padding: 6px 12px;
    background-color: #198754;
    color: #fff;
    border-radius: 6px;
    text-decoration: none;
    margin-right: 5px;
    transition: all 0.3s ease;
}

td a.edit-btn:hover {
    background-color: #157347;
    transform: translateY(-1px);
}

td button.delete-btn {
    padding: 6px 12px;
    background-color: #dc3545;
    color: #fff;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: all 0.3s ease;
}

td button.delete-btn:hover {
    background-color: #b02a37;
    transform: translateY(-1px);
}
</style>

<div class="container">
    <h2>Vehicles</h2>
    <a href="/vehicles/create" class="new-btn">+ New Vehicle</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Number</th>
                <th>Capacity</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${vehicles}" var="v">
                <tr>
                    <td>${v.id}</td>
                    <td>${v.type}</td>
                    <td>${v.number}</td>
                    <td>${v.capacity}</td>
                    <td>${v.description}</td>
                    <td>
                        <a href="/vehicles/${v.id}/edit" class="edit-btn">Edit</a>
                        <form action="/vehicles/${v.id}/delete" method="post" style="display:inline">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="delete-btn" onclick="return confirm('Delete this vehicle?')">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../fragments/footer.jspf" %>
