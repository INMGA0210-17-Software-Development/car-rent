# Car Rental Backend System

A backend system for a car rental platform, implemented using Spring Boot.  
The application provides core functionalities such as user registration and authentication, car listing management, searching and filtering, booking creation, and rental cost calculation.

This project serves as the backend component of a team-based software development assignment.

---

## 1. Features Overview

### 1.1 User Management
- Register as Rentee or Renter
- Login using email and password
- User role management (RENTEE / RENTER / ADMIN)

### 1.2 Car Listing Management (CRUD)
- Add new car listings
- Update car information
- Delete car listings
- Retrieve all car records

### 1.3 Search and Filtering
- Search cars based on:
    - Location
    - Brand
    - Availability
    - Sorting by price (ascending/descending)

### 1.4 Booking System
- Create bookings
- Check availability
- Maintain booking status (CREATED / PAID / CANCELLED)

### 1.5 Rental Cost Calculation
- Calculate total rental cost based on:
    - Start date
    - End date
    - Vehicle daily price

### 1.6 Database Layer
- Uses in-memory H2 database
- Tables auto-generated using JPA and Hibernate
- H2 Console available at `/h2-console`

## Frontend API Interface 
- See: API interface documentation.md


---

## 2. Technology Stack

| Component           | Technology                         |
|--------------------|------------------------------------|
| Programming Language | Java 17                           |
| Framework           | Spring Boot 3.3                    |
| Database            | H2 In-Memory Database              |
| ORM                 | JPA / Hibernate                    |
| Build Tool          | Maven                              |
| Architecture        | RESTful API                        |
| Version Control     | Git & GitHub                       |

---

## 3. Project Structure

