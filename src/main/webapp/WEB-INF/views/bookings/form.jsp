<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../fragments/header.jspf" %>
<h2>${booking.id == null ? "Create Booking" : "Edit Booking"}</h2>
<form action="/bookings/save" method="post">
    <input type="hidden" name="id" value="${booking.id}"/>
    <label>Vehicle:
        <select name="vehicle.id">
            <c:forEach items="${vehicles}" var="v">
                <option value="${v.id}" ${booking.vehicle != null && v.id == booking.vehicle.id ? "selected" : ""}>
                    ${v.type} - ${v.number}
                </option>
            </c:forEach>
        </select>
    </label><br/>
    <label>From: <input type="text" name="fromCity" value="${booking.fromCity}"/></label><br/>
    <label>To: <input type="text" name="toCity" value="${booking.toCity}"/></label><br/>
    <label>Departure: <input type="datetime-local" name="departureTime" value="${booking.departureTime}"/></label><br/>
    <label>Arrival: <input type="datetime-local" name="arrivalTime" value="${booking.arrivalTime}"/></label><br/>
    <label>Status:
        <select name="status">
            <c:forEach items="${statuses}" var="s">
                <option value="${s}" ${s == booking.status ? "selected" : ""}>${s}</option>
            </c:forEach>
        </select>
    </label><br/>
    <label>Price: <input type="number" step="0.01" name="price" value="${booking.price}"/></label><br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Save</button>
</form>
<%@ include file="../fragments/footer.jspf" %>
