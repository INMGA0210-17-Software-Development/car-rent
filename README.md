# Car Rental Backend System

A backend system for a car rental platform, built with Java and Spring Boot.  
The application provides core functionalities such as user registration/login, car listing management, search and filtering, booking, and rental price calculation.

This project serves as the backend component of the **Software Engineering and Development 25 Fall** course project.

---

## 1. Features Overview

### 1.1 User Management
- Register as **Rentee** or **Renter**
- Login using email and password
- User role management (RENTEE / RENTER / ADMIN)

### 1.2 Car Listing Management (CRUD)
- Add new car listings
- Update car information
- Delete car listings
- Retrieve all car records

### 1.3 Search and Filtering
Search cars based on:
- Location
- Brand
- Availability
- Sorting by price (ascending/descending)

### 1.4 Booking System
- Create bookings
- View booking history
- Cancel bookings
- Track booking status (CREATED, CONFIRMED, CANCELLED)

### 1.5 Rental Cost Calculation
- Automatic calculation based on car daily price × rental days
- Return total cost to frontend via API

### 1.6 Frontend Integration Support
- API follows RESTful standards
- Clear request/response structure for frontend to consume
- H2 Console available at: `/h2-console`

---

## 2. Tech Stack

| Component      | Technology         |
|----------------|--------------------|
| Framework      | Spring Boot 3.3     |
| ORM            | JPA / Hibernate     |
| Database       | H2 (in-memory)      |
| Build Tool     | Maven               |
| Language       | Java 17+            |

---

## 3. API Overview

### **User APIs**
- `POST /api/auth/register` — Register user
- `POST /api/auth/login` — Login
- `GET /api/users/{id}` — Get user info

### **Car Listing APIs**
- `POST /api/cars` — Add new car
- `PUT /api/cars/{id}` — Update car info
- `DELETE /api/cars/{id}` — Delete car
- `GET /api/cars` — List all cars
- `GET /api/cars/search` — Search with filters

### **Booking APIs**
- `POST /api/bookings` — Create booking
- `GET /api/bookings/user/{id}` — Get user bookings
- `PUT /api/bookings/{id}/cancel` — Cancel booking

### **Pricing API**
- `POST /api/pricing/calculate`  
  Returns:
```json
{
  "totalPrice": 120.00,
  "days": 3,
  "dailyPrice": 40.00
}
