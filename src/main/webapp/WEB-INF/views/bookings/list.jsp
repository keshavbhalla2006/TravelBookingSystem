<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../fragments/header.jspf" %>
<h2>Bookings</h2>
<a href="/bookings/create">+ New Booking</a>
<table border="1" cellpadding="6" cellspacing="0">
    <thead>
    <tr><th>ID</th><th>User</th><th>Vehicle</th><th>Route</th><th>Departure</th><th>Arrival</th><th>Status</th><th>Price</th><th>Actions</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${bookings}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.user.name} (${b.user.email})</td>
            <td>${b.vehicle.number}</td>
            <td>${b.fromCity} → ${b.toCity}</td>
            <td>${b.departureTime}</td>
            <td>${b.arrivalTime}</td>
            <td>${b.status}</td>
            <td>${b.price}</td>
            <td>
                <a href="/bookings/${b.id}/edit">Edit</a>
                <form action="/bookings/${b.id}/delete" method="post" style="display:inline">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" onclick="return confirm('Delete this booking?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="../fragments/footer.jspf" %>
