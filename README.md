![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Build](https://img.shields.io/badge/Build-Maven-orange)
![Status](https://img.shields.io/badge/Status-Active-success)


# Omutwar Registration Service

The Omutwar Registration Service is the identity and onboarding backend for the Omutwar platform.  
It provides secure user creation, invitation-based onboarding, and the foundation for partner and vendor access.

---

## Features

- Invitation-based account creation  
- Secure password hashing  
- User lifecycle management  
- Modular, enterprise-grade architecture  
- Ready for role-based access and multi-tenant expansion  

---

## Tech Stack

- Java 21  
- Spring Boot  
- Spring Security  
- JPA / Hibernate  
- PostgreSQL  

---

## Project Structure

```
src/main/java/com/omutwar/registration
│
├── auth/          # Authentication flows
├── config/        # Spring Boot configuration
├── controller/    # REST API endpoints
├── domain/        # JPA entities
├── dto/           # Data transfer objects
├── invitation/    # Invitation-based onboarding
├── repository/    # Data access layer
├── request/       # API request payloads
├── security/      # Password + security configuration
└── service/       # Business logic
```

---

## Running Locally

```bash
./mvnw spring-boot:run
```

Service runs at:

```
http://localhost:8080
```

---

## Status

Actively developed as the core identity and onboarding service for the Omutwar platform.
```

---

## API Overview

### Invitations

**POST /api/invitations/partner**  
Create a partner invitation.

**POST /api/invitations/vendor**  
Create a vendor invitation.

**GET /api/invitations/validate?token=...**  
Validate an invitation token.

**POST /api/invitations/complete**  
Complete onboarding and create a user.

### Users

**GET /api/users**  
List all users.

**GET /api/users/{id}**  
Get a single user.

**POST /api/users**  
Create a user.

**PUT /api/users/{id}**  
Update a user.

**DELETE /api/users/{id}**  
Delete a user.
