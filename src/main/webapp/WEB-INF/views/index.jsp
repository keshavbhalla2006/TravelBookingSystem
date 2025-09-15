<%@ include file="fragments/header.jspf" %>
<style>
    /* --- Body & Section Styling --- */
    section {
        max-width: 900px;
        margin: 50px auto;
        padding: 40px;
        background: linear-gradient(145deg, #f8f9fa, #e9ecef);
        border-radius: 15px;
        box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    h2 {
        text-align: center;
        font-size: 2.5rem;
        color: #343a40;
        margin-bottom: 20px;
    }

    p {
        font-size: 1.1rem;
        line-height: 1.6;
        color: #495057;
        text-align: center;
        margin-bottom: 30px;
    }

    ul {
        list-style: none;
        padding: 0;
        display: flex;
        justify-content: center;
        gap: 30px;
        flex-wrap: wrap;
        margin-bottom: 30px;
    }

    ul li {
        background: #ffffff;
        padding: 15px 25px;
        border-radius: 10px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.05);
        transition: all 0.3s ease;
    }

    ul li:hover {
        transform: translateY(-5px);
        box-shadow: 0 10px 20px rgba(0,0,0,0.15);
    }

    ul li a {
        text-decoration: none;
        color: #0d6efd;
        font-weight: 500;
        font-size: 1.1rem;
    }

    code {
        background: #f1f3f5;
        padding: 2px 6px;
        border-radius: 5px;
        font-size: 0.95rem;
    }

    /* Footer tweak */
    section p:last-child {
        text-align: center;
        margin-top: 20px;
        font-size: 1rem;
        color: #6c757d;
    }
</style>

<section>
    <h2>Welcome to Travelify</h2>
    <p>This is a demo Travel Booking System built with Spring Boot, Spring MVC (JSP), Spring Security (form login + Google OAuth2), and Hibernate/JPA.</p>

    <ul>
        <li><a href="/vehicles">Manage Vehicles</a></li>
        <li><a href="/bookings">Create & Manage Bookings</a></li>
        <li><a href="/admin/users">Manage Users (ADMIN only)</a></li>
    </ul>

    <p>Seed Admin user: <code>admin@local</code> / <code>admin123</code></p>
</section>

<%@ include file="fragments/footer.jspf" %>
