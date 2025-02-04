# Desk Booker System

## Description
The Desk Booker System is a secure, role-based access application for managing employees, desks, and reservations in an office environment. It ensures efficient desk allocation while enforcing authentication and authorization using JWT tokens.

## Technologies Used
- **Backend**: Spring Boot, Spring Security  
- **Database**: PostgreSQL  
- **Authentication/Authorization**: Spring Security with JWT-based role-based access control  
- **Build Tool**: Maven  

## Getting Started

### Prerequisites
- Git  
- Java 17+  
- Maven 3.8+  
- PostgreSQL database  
- Postman (for testing)  

### Installation Steps

1. Clone the repository:
   ```sh
   git clone https://github.com/petrovska4/desk-booker-project.git
   ```
2. Navigate to the project directory:
   ```sh
   cd desk-booker
   ```
3. Set up the database:
   - Create a PostgreSQL database (`deskbooker`).
   - Configure the connection details in `application.yml` (such as your username and password).
4. Build the project:
   ```sh
   mvn clean install
   ```
5. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Documentation

### REST Endpoints
#### Desks
- **POST** `/desk/create` - Create a new desk *(Directors only)*
- **DELETE** `/desk/delete?id={id}` - Delete a desk
- **GET** `/desk/get-all-by-date-range?from={date}&to={date}` - Get available desks within a date range

#### Reservations
- **POST** `/reservation/create` - Create a new reservation
- **GET** `/reservation/get-all-by-employee-id` - Get all reservations by logged-in employee
- **DELETE** `/reservation/delete?id={id}` - Delete reservation

### SOAP Endpoints
- **POST** `/ws/officeServiceWsdl`, **header**: `AddOfficeRequest` - Create office
- **POST** `/ws/officeServiceWsdl`, **header**: `ListAllOfficesRequest` - List all offices
- **POST** `/ws/deskServiceWsd`, **header**: `UpdateDeskRequest` - Update desk
- **POST** `/ws/employeeServiceWsdl`, **header**: `GetEmployeeRequest` - Get employee by ID
- **POST** `/ws/employeeServiceWsdl`, **header**: `GetEmployeesByPositionRequest` - Get employees by position

## Database Schema
- **Office** – One-to-Many – **Desk**
- **Desk** – One-to-Many – **Reservation**
- **Reservation** – Many-to-One – **Employee**

## Security
### Role-Based Access Control
Roles are derived from `EmployeePositionEnum`:
- **DIRECTOR** – Can create, update, and delete desks/offices.
- **EMPLOYEE** – Can reserve or cancel a desk reservation.

### Authentication Flow
- Users authenticate via **JWT (JSON Web Tokens)**.
- Passwords are securely hashed using **BCryptPasswordEncoder**.
- Endpoints are secured with:
  - `@PreAuthorize` annotations (method-level security).
  - Role-based access checks using `ROLE_` prefixed authorities.
  - JWT filters for request validation.

## Contributing
**GitHub Repository:** [Desk Booker Project](https://github.com/petrovska4/desk-booker-project.git)

1. Fork the repository.
2. Create a new branch:
   ```sh
   git checkout -b feature-branch
   ```
3. Make your changes.
4. Commit your changes:
   ```sh
   git commit -am 'Add new feature'
   ```
5. Push to the branch:
   ```sh
   git push origin feature-branch
   ```
6. Create a new Pull Request.
