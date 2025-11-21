Car Rental Platform API Documentation
Overview
This document describes all backend API interfaces required for the frontend of the car rental platform. All interfaces use the HTTPS protocol, with the base URL: https://your-api-domain.com/api
Authentication Method
Except for public interfaces such as login and registration, all other interfaces require a JWT Token in the request header:
plaintext
Authorization: Bearer <your-jwt-token>
General Response Format
All API interfaces follow a unified response format:
json
{
"success": true,
"message": "Operation successful",
"data": {
// Specific data content
}
}
Error response format:
json
{
"success": false,
"message": "Error description",
"error": "Detailed error information"
}
1. Authentication-related Interfaces
   1.1 User Login
   Endpoint: POST /auth/login
   Function: User login authentication
   Request Body:
   json
   {
   "email": "user@example.com",
   "password": "password123",
   "rememberMe": false
   }
   Response:
   json
   {
   "success": true,
   "token": "jwt-token-string",
   "user": {
   "id": 1,
   "email": "user@example.com",
   "name": "User Name",
   "role": "rentee",
   "phone": "+36 20 123 4567"
   }
   }
   1.2 User Registration
   Endpoint: POST /auth/register
   Function: New user registration
   Request Body:
   json
   {
   "fullName": "Zhang San",
   "email": "zhangsan@example.com",
   "password": "password123",
   "userType": "rentee"
   }
   Response:
   json
   {
   "success": true,
   "message": "Registration successful. Please check your email to verify your account.",
   "user": {
   "id": 2,
   "email": "zhangsan@example.com",
   "name": "Zhang San",
   "role": "rentee"
   }
   }
   1.3 Social Login
   Endpoint: POST /auth/social-login
   Function: Third-party social platform login
   Request Body:
   json
   {
   "provider": "google",
   "token": "social-platform-token"
   }
   1.4 Forgot Password
   Endpoint: POST /auth/forgot-password
   Function: Send password reset email
   Request Body:
   json
   {
   "email": "user@example.com"
   }
   1.5 Reset Password
   Endpoint: POST /auth/reset-password
   Function: Set new password using reset token
   Request Body:
   json
   {
   "token": "reset-token",
   "newPassword": "newpassword123"
   }
   1.6 Verify Email
   Endpoint: POST /auth/verify-email
   Function: Verify user email
   Request Body:
   json
   {
   "token": "verification-token"
   }
   1.7 Get Current User Information
   Endpoint: GET /auth/me
   Function: Retrieve current logged-in user information
   Response:
   json
   {
   "success": true,
   "user": {
   "id": 1,
   "email": "user@example.com",
   "name": "User Name",
   "role": "rentee",
   "phone": "+36 20 123 4567"
   }
   }
   1.8 Logout
   Endpoint: POST /auth/logout
   Function: User logout
2. Car-related Interfaces
   2.1 Get All Rentable Cars List
   Endpoint: GET /cars
   Function: Retrieve all rentable cars for homepage display
   Query Parameters:
   page: Page number (default: 1)
   limit: Items per page (default: 20)
   search: Search keyword (car name or location)
   location: Location filter
   carType: Car type filter (Luxury, Sports, SUV, Electric, Family)
   priceSort: Price sorting (Price Ascending, Price Descending)
   transmission: Transmission type (Automatic, Manual)
   availableOnly: Show available cars only (true/false)
   Response:
   json
   {
   "success": true,
   "cars": [
   {
   "id": 1,
   "name": "Mercedes-Benz S-Class",
   "price": 299,
   "image": "https://example.com/car-image.jpg",
   "category": "Luxury",
   "available": true,
   "rating": 4.9,
   "reviews": 127,
   "location": "Budapest V. District",
   "passengers": 5,
   "fuel": "Petrol",
   "transmission": "Automatic"
   }
   ],
   "total": 50,
   "page": 1,
   "limit": 20
   }
   2.2 Get Car Details
   Endpoint: GET /cars/:id
   Function: Retrieve detailed information of a specific car
   Response:
   json
   {
   "success": true,
   "car": {
   "id": 1,
   "name": "Mercedes-Benz S-Class",
   "description": "Luxury sedan, suitable for business travel",
   "price": 299,
   "image": "https://example.com/car-image.jpg",
   "category": "Luxury",
   "available": true,
   "rating": 4.9,
   "reviews": 127,
   "location": "Budapest V. District",
   "passengers": 5,
   "fuel": "Petrol",
   "transmission": "Automatic",
   "features": ["Air Conditioning", "GPS Navigation", "Bluetooth"],
   "ownerId": 10,
   "ownerName": "Car Owner Name"
   }
   }
   2.3 Get User's Owned Cars List
   Endpoint: GET /cars/my-cars
   Function: Retrieve list of cars owned by current user (My Cars page)
   Authentication Required: Yes
   Response:
   json
   {
   "success": true,
   "cars": [
   {
   "id": 1,
   "name": "Mercedes-Benz S-Class",
   "image": "https://example.com/car-image.jpg",
   "location": "Budapest V. District",
   "passengers": 5,
   "fuel": "Petrol",
   "transmission": "Automatic",
   "status": "Available",
   "totalRentals": 89,
   "rating": 4.9,
   "reviews": 127
   }
   ]
   }
   2.4 Add New Car
   Endpoint: POST /cars
   Function: User adds a new car to the platform
   Authentication Required: Yes
   Request Body:
   json
   {
   "name": "BMW X5",
   "location": "Budapest II. District",
   "image": "https://example.com/car-image.jpg",
   "passengers": 7,
   "fuel": "Diesel",
   "transmission": "Automatic",
   "description": "Spacious SUV, suitable for family travel",
   "features": ["Air Conditioning", "GPS Navigation", "Bluetooth"],
   "dailyRate": 350
   }
   Response:
   json
   {
   "success": true,
   "car": {
   "id": 10,
   "name": "BMW X5",
   "location": "Budapest II. District",
   "image": "https://example.com/car-image.jpg",
   "passengers": 7,
   "fuel": "Diesel",
   "transmission": "Automatic",
   "description": "Spacious SUV, suitable for family travel",
   "features": ["Air Conditioning", "GPS Navigation", "Bluetooth"],
   "dailyRate": 350,
   "status": "Available",
   "totalRentals": 0,
   "rating": 0,
   "reviews": 0
   }
   }
   2.5 Update Car Information
   Endpoint: PUT /cars/:id
   Function: Update car information
   Authentication Required: Yes
   Request Body: Same as Add Car endpoint
   2.6 Delete Car
   Endpoint: DELETE /cars/:id
   Function: Delete a car
   Authentication Required: Yes
   Response:
   json
   {
   "success": true,
   "message": "Car has been successfully deleted"
   }
3. Booking-related Interfaces
   3.1 Get User's Bookings List
   Endpoint: GET /bookings/my-bookings
   Function: Retrieve user's booking list (used in Messages page)
   Authentication Required: Yes
   Response:
   json
   {
   "success": true,
   "bookedByYou": [
   {
   "bookingId": 1,
   "carId": 1,
   "carName": "Mercedes-Benz S-Class",
   "carImage": "https://example.com/car-image.jpg",
   "ownerId": 101,
   "ownerName": "László Kovács",
   "location": "Budapest V. District",
   "startDate": "2025-11-15",
   "endDate": "2025-11-17",
   "startTime": "10:00",
   "endTime": "18:00",
   "price": 598
   }
   ],
   "bookedBySomeone": [
   {
   "bookingId": 3,
   "carId": 3,
   "carName": "BMW X5 M Sport",
   "carImage": "https://example.com/car-image.jpg",
   "renterId": 201,
   "renterName": "Zsófia Varga",
   "location": "Budapest II. District",
   "startDate": "2025-11-16",
   "endDate": "2025-11-18",
   "startTime": "08:00",
   "endTime": "20:00",
   "price": 720
   }
   ]
   }
   3.2 Create New Booking
   Endpoint: POST /bookings
   Function: Create a new car booking
   Authentication Required: Yes
   Request Body:
   json
   {
   "carId": 1,
   "startDate": "2025-11-20",
   "endDate": "2025-11-22",
   "startTime": "09:00",
   "endTime": "17:00"
   }
   3.3 Cancel Booking
   Endpoint: DELETE /bookings/:id
   Function: Cancel a booking
   Authentication Required: Yes
4. Rental Record-related Interfaces
   4.1 Get All Rental Records
   Endpoint: GET /rentals/all
   Function: Retrieve all rental records for user's cars (Car List Management page)
   Authentication Required: Yes
   Response:
   json
   {
   "success": true,
   "rentals": [
   {
   "id": 1,
   "carId": 1,
   "carName": "Mercedes-Benz S-Class",
   "carImage": "https://example.com/car-image.jpg",
   "renterName": "János Kovács",
   "renterEmail": "janos.kovacs@email.com",
   "location": "Budapest V. District",
   "startDate": "2025-11-13T10:00:00Z",
   "endDate": "2025-11-16T10:00:00Z",
   "duration": 3,
   "dailyRate": 299,
   "totalAmount": 897,
   "status": "Completed"
   }
   ],
   "summary": {
   "totalRevenue": 3074,
   "activeRentals": 1,
   "upcomingRentals": 2
   }
   }
5. Message-related Interfaces
   5.1 Get Chat Messages for Specific Booking
   Endpoint: GET /messages/booking/:bookingId
   Function: Retrieve chat message records for a specific booking
   Authentication Required: Yes
   Response:
   json
   {
   "success": true,
   "messages": [
   {
   "id": 1,
   "senderId": 1,
   "receiverId": 101,
   "content": "Hi! I'm interested in renting your car.",
   "timestamp": "2025-11-10T10:30:00Z",
   "senderName": "You"
   },
   {
   "id": 2,
   "senderId": 101,
   "receiverId": 1,
   "content": "Hello! The car is available. When would you like to pick it up?",
   "timestamp": "2025-11-10T10:32:00Z",
   "senderName": "László Kovács"
   }
   ]
   }
   5.2 Send Message
   Endpoint: POST /messages
   Function: Send a chat message
   Authentication Required: Yes
   Request Body:
   json
   {
   "bookingId": 1,
   "receiverId": 101,
   "content": "Great! I'd like to pick it up on Nov 15 at 10:00 AM."
   }
   Response:
   json
   {
   "success": true,
   "message": {
   "id": 3,
   "senderId": 1,
   "receiverId": 101,
   "content": "Great! I'd like to pick it up on Nov 15 at 10:00 AM.",
   "timestamp": "2025-11-10T10:35:00Z",
   "senderName": "You"
   }
   }
6. Review-related Interfaces
   6.1 Get User's Reviews List
   Endpoint: GET /reviews/my-reviews
   Function: Retrieve all reviews written by the user (My Reviews page)
   Authentication Required: Yes
   Response:
   json
   {
   "success": true,
   "reviews": [
   {
   "id": 1,
   "carId": 1,
   "carName": "Mercedes-Benz S-Class",
   "carImage": "https://example.com/car-image.jpg",
   "location": "Budapest V. District",
   "date": "2024-10-15T00:00:00Z",
   "rating": 5,
   "reviewText": "Absolutely loved this car! The ride was smooth and comfortable."
   }
   ],
   "statistics": {
   "totalReviews": 4,
   "averageRating": 4.5,
   "fiveStarReviews": 2
   }
   }
   6.2 Add Review
   Endpoint: POST /reviews
   Function: Add a new review
   Authentication Required: Yes
   Request Body:
   json
   {
   "carId": 1,
   "rating": 5,
   "reviewText": "Excellent car and service!"
   }
   6.3 Update Review
   Endpoint: PUT /reviews/:id
   Function: Update a review
   Authentication Required: Yes
   Request Body:
   json
   {
   "rating": 4.5,
   "reviewText": "Updated review text"
   }
   6.4 Delete Review
   Endpoint: DELETE /reviews/:id
   Function: Delete a review
   Authentication Required: Yes
7. User-related Interfaces
   7.1 Get User Profile
   Endpoint: GET /users/profile
   Function: Retrieve user personal profile (Profile page)
   Authentication Required: Yes
   Response:
   json
   {
   "success": true,
   "personalInfo": {
   "firstName": "John",
   "lastName": "Doe",
   "email":"john.doe@example.com",
   "phone": "+36 20 123 4567"},
   "licenseInfo": {"number": "AB123456","issueDate": "2020-01-15","expiryDate": "2030-01-15"},
   "paymentMethods": [{"id": 1,"brand": "Visa","last4": "4242","expires": "12/25","isDefault": true}]

}
### 7.2 Update User Profile
- **Endpoint**: `PUT /users/profile`
- **Function**: Update user personal profile
- **Authentication Required**: Yes
- **Request Body**:
```json
{
  "personalInfo": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "+36 20 123 4567"
  },
  "licenseInfo": {
    "number": "AB123456",
    "issueDate": "2020-01-15",
    "expiryDate": "2030-01-15"
  }
}
7.3 Get User Settings
Endpoint: GET /users/settings
Function: Retrieve user settings (Settings page)
Authentication Required: Yes
Response:
json
{
  "success": true,
  "settings": {
    "notifications": {
      "emailNotifications": true,
      "bookingReminders": true,
      "promotionalEmails": false
    },
    "regionalSettings": {
      "language": "English",
      "currency": "USD ($)"
    }
  }
}
7.4 Update User Settings
Endpoint: PUT /users/settings
Function: Update user settings
Authentication Required: Yes
Request Body:
json
{
  "notifications": {
    "emailNotifications": true,
    "bookingReminders": true,
    "promotionalEmails": false
  },
  "regionalSettings": {
    "language": "English",
    "currency": "USD ($)"
  }
}
7.5 Add Payment Method
Endpoint: POST /users/payment-methods
Function: Add a new payment method
Authentication Required: Yes
Request Body:
json
{
  "cardNumber": "4242424242424242",
  "expiryMonth": 12,
  "expiryYear": 2025,
  "cvv": "123",
  "cardholderName": "John Doe"
}
Response:
json
{
  "success": true,
  "paymentMethod": {
    "id": 2,
    "brand": "Visa",
    "last4": "4242",
    "expires": "12/25",
    "isDefault": false
  }
}
7.6 Set Default Payment Method
Endpoint: PUT /users/payment-methods/:id/set-default
Function: Set default payment method
Authentication Required: Yes
7.7 Delete Payment Method
Endpoint: DELETE /users/payment-methods/:id
Function: Delete a payment method
Authentication Required: Yes
Error Code Explanation
Status Code	Description
200	Request successful
201	Creation successful
400	Bad request (invalid parameters)
401	Unauthorized (login required)
403	Forbidden (insufficient permissions)
404	Resource not found
409	Resource conflict
422	Request parameter validation failed
500	Internal server error
Data Type Explanation
Car Status
Available: Available for rental
Rented: Currently rented
Maintenance: Under maintenance
Booking Status
Pending: Pending confirmation
Confirmed: Confirmed
Active: In progress
Completed: Completed
Cancelled: Cancelled
Rental Status
Upcoming: Scheduled to start
Active: In progress
Completed: Completed
User Role
rentee: Renter
admin: Administrator
Notes
All date and time fields use ISO 8601 format (YYYY-MM-DDTHH:mm:ssZ)
All price fields are in US Dollars, rounded to two decimal places
Image URLs must support HTTPS protocol
Pagination queries default to 20 items per page, with a maximum of 100 items
Search functionality supports fuzzy matching
All sensitive information (e.g., passwords, payment details) must be transmitted and stored securely
APIs must support Cross-Origin Resource Sharing (CORS)
It is recommended to implement request rate limiting to prevent abuse