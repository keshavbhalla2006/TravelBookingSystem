# Travel Booking System (Spring Boot + MVC JSP + Security + OAuth2 + JPA MySQL)

A simple end-to-end demo project that manages **Users, Vehicles, and Bookings** with:
- Spring Boot 3 (Java 17)
- Spring MVC with **JSP** views
- Spring Security: **Form Login** and **Google OAuth2**
- Hibernate / JPA with **MySQL**
- Demonstrates **Hibernate Entity Lifecycle** callbacks (@PrePersist, @PreUpdate, @PostLoad)

---

## 1) Prerequisites

- **Java 17** installed and set as your IDE/JDK.
- **Maven 3.9+** (IntelliJ will handle it automatically).
- **MySQL Server** (8.x) and **MySQL Workbench**.

### Create DB (MySQL Workbench)
Open a SQL tab and run:
```sql
-- create database (if not exists)
CREATE DATABASE IF NOT EXISTS travel_booking CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- (optional) create a dedicated MySQL user
CREATE USER IF NOT EXISTS 'travelapp'@'%' IDENTIFIED BY 'travelpass';
GRANT ALL PRIVILEGES ON travel_booking.* TO 'travelapp'@'%';
FLUSH PRIVILEGES;
```

Then configure `src/main/resources/application.properties` datasource credentials.

---

## 2) Import into IntelliJ IDEA Ultimate

1. **File → New → Project from Existing Sources…** and select the project root (`travel-booking-system`).
2. IntelliJ detects Maven and imports dependencies.
3. Ensure **JDK 17** is selected for the project and modules.

---

## 3) Configure OAuth2 (Google) — optional but recommended

1. Go to **Google Cloud Console → APIs & Services → Credentials**.
2. Create **OAuth 2.0 Client ID** (Application type: *Web application*).
3. Authorized redirect URI (default): `http://localhost:8080/login/oauth2/code/google`
4. Put the **client-id** and **client-secret** into `application.properties`:
```
spring.security.oauth2.client.registration.google.client-id=...
spring.security.oauth2.client.registration.google.client-secret=...
```

You can still use local form login with the seeded user below without OAuth.

---

## 4) Run

- Start MySQL.
- In IntelliJ, run the main class: `TravelBookingSystemApplication`.
- Open `http://localhost:8080`.

### Seeded Admin
- **Email**: `admin@local`
- **Password**: `admin123`

---

## 5) App Features (How to use)

- **Vehicles**: Create, edit, delete at `/vehicles`.
- **Bookings**: Create bookings at `/bookings` (attach a vehicle, set route, times, price).
- **Users (Admin)**: Manage users at `/admin/users`.
- **Login**: `/login` page supports **form login** and **Google OAuth2**.

> Booking list shows your own bookings when logged in as a normal user; Admin sees all bookings.

---

## 6) Notes / Tips

- JSP support uses:
  - `tomcat-embed-jasper`
  - `jakarta.servlet.jsp.jstl` (API + impl)
- JPA will create/update tables with `spring.jpa.hibernate.ddl-auto=update`. For production, use migrations (Flyway/Liquibase).
- Entity lifecycle callbacks are implemented in `User` and `Booking` as examples.
- CSRF tokens are included in all forms.

---

## 7) Common Issues

- **JSP 404**: Make sure views are under `src/main/webapp/WEB-INF/views/` and prefix/suffix are set.
- **Driver / SSL**: If you see MySQL SSL or pub key errors, keep `allowPublicKeyRetrieval=true&useSSL=false` in the JDBC URL (local dev only).
- **Time zone**: Set `serverTimezone=UTC` in URL to avoid tz warnings (already present).

---

## 8) Next Steps / Enhancements

- Add search/filtering for vehicles and bookings.
- Pagination & sorting (Spring Data).
- Validation messages & error pages.
- Replace JSP with modern frontend (Thymeleaf, React, etc.).

Enjoy!
