Car Rental Backend System
A Spring Boot–based backend system for a car rental platform.  
Supports user registration/login, car listing management, searching, booking, and rental price calculation.

This project is part of the **Software Development** course,  
and serves as the backend component of a collaborative front-end + back-end team project.

---
Features Overview

1.User Management
- Register as **Rentee** or **Renter**
- Login via email + password
- User role management (RENTEE / RENTER / ADMIN)

2.Car Listing (CRUD)
- Create car listing (brand, model, price, location)
- Update car info
- Delete car listing
- View/search available cars

3.Search & Filtering
- Filter cars by:
  - Location
  - Brand
  - Availability
  - Price sorting

4.Booking System
- Create a booking request
- Check availability
- Booking status tracking

5.Rental Cost Calculation
- Calculate cost based on:
  - start date
  - end date
  - daily price
- Days calculation using Java Time API

6.In-Memory H2 Database
- Auto table creation via Hibernate
- H2 Console available at `/h2-console`

---

7.Tech Stack

| Component           | Choice                                 |
|---------------------|------------------------------------------|
| Language            | Java 17                                  |
| Framework           | Spring Boot 3.3                          |
| Database            | H2 In-Memory DB                          |
| ORM                 | JPA / Hibernate                          |
| Build Tool          | Maven                                    |
| API Specification   | RESTful                                   |
| Version Control     | Git + GitHub                              |


