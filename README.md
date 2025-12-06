# A2Z - Multi-Vendor E-Commerce Platform

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-blue)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

A modern, scalable, and production-ready Spring Boot e-commerce platform designed to support multiple vendors (sellers) with a comprehensive buyer interface. Built with industry best practices, JWT authentication, OTP-based verification, and email notifications.

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Project Structure](#project-structure)
- [Configuration Guide](#configuration-guide)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Authentication & Security](#authentication--security)
- [Known Issues & Solutions](#known-issues--solutions)
- [Development Workflow](#development-workflow)
- [Deployment Guide](#deployment-guide)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

**A2Z** is a full-featured e-commerce backend built with Spring Boot 3, designed for handling multiple vendor ecosystems. The platform provides:

- **Multi-Role Support**: Buyer, Seller, and Admin functionalities
- **Secure Authentication**: JWT tokens with OTP-based email verification
- **Vendor Management**: Complete seller onboarding, profile, and product management
- **Shopping Features**: Products, Cart, Orders, Wishlist, Coupons, and Reviews
- **Payment Integration**: Support for multiple payment methods
- **Real-time Notifications**: Email notifications for orders, OTP, and system updates
- **Scalable Architecture**: Designed for horizontal scaling and microservices migration

### Why A2Z?

- **Production-Ready**: Follows industry-standard patterns and best practices
- **Secure**: JWT-based authentication, role-based access control (RBAC)
- **Maintainable**: Clean code architecture with clear separation of concerns
- **Extensible**: Easy to add new features and payment gateways
- **Well-Documented**: Comprehensive JavaDoc and inline documentation

## ✨ Key Features

### Authentication & Authorization
- ✅ OTP-based login/signup via email
- ✅ JWT token generation with refresh mechanism
- ✅ Role-based access control (RBAC) - Buyer, Seller, Admin
- ✅ Secure password encoding with BCrypt
- ✅ Email verification for account security
- ✅ Custom JWT token validator filter

### Seller Features
- ✅ Seller registration and account management
- ✅ Business details and bank account information
- ✅ Product catalog management (CRUD operations)
- ✅ Inventory/Stock management
- ✅ Sales reports and analytics
- ✅ Multiple product categories
- ✅ Pickup address management

### Buyer Features
- ✅ User account management
- ✅ Shopping cart functionality
- ✅ Wishlist management
- ✅ Order placement and tracking
- ✅ Order history and reviews
- ✅ Coupon/Discount application
- ✅ Multiple delivery addresses
- ✅ Payment method selection

### Platform Features
- ✅ Product search and filtering by category
- ✅ Home page banner management and deals
- ✅ Multi-category support
- ✅ Order status tracking (Pending, Confirmed, Shipped, Delivered, Cancelled)
- ✅ Payment status management
- ✅ Centralized exception handling
- ✅ Comprehensive logging

## 🛠️ Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Framework** | Spring Boot | 3.5.7 |
| **Language** | Java | 17 LTS |
| **Database** | MySQL | 8.0+ |
| **ORM** | Hibernate (JPA) | Jakarta Persistence |
| **Authentication** | JWT (JJWT) | 0.11.1 |
| **Security** | Spring Security | 3.5.7 |
| **Build Tool** | Apache Maven | 3.8+ |
| **Code Generation** | Lombok | 1.18.42 |
| **Email Service** | Spring Boot Mail | 3.5.7 |
| **Validation** | Spring Validation | 3.5.7 |
| **Database Driver** | MySQL Connector/J | Latest |
| **Testing** | JUnit 5, Mockito | 3.5.7 |

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: JDK 17 or higher
  ```bash
  java -version
  ```
- **Maven**: 3.8.1 or higher
  ```bash
  mvn -version
  ```
- **MySQL Server**: 8.0 or higher (or any compatible MySQL variant)
  ```bash
  mysql --version
  ```
- **Git**: For version control
  ```bash
  git --version
  ```
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions
- **Postman/Insomnia**: For testing API endpoints

## 🚀 Installation & Setup

### Step 1: Clone the Repository

```bash
git clone https://github.com/chetanandmeher/a2z-ecommerce.git
cd ecommerce-multivendor
```

### Step 2: Create Database

Open MySQL client and execute:

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS a2z;
USE a2z;

-- Verify database creation
SHOW DATABASES;
```

Or using command line:

```bash
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS a2z;"
```

### Step 3: Configure Application Properties

Edit `src/main/resources/application.properties`:

```properties
# ==========================================
# Server Configuration
# ==========================================
spring.application.name=ecommerce-multivendor
server.port=5454
server.servlet.context-path=/api

# ==========================================
# Database Configuration
# ==========================================
spring.datasource.url=jdbc:mysql://localhost:3306/a2z
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true

# ==========================================
# Email Configuration (Gmail)
# ==========================================
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password  # Use App Password for Gmail
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000

# ==========================================
# Logging Configuration
# ==========================================
logging.level.root=INFO
logging.level.com.a2z=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.file.name=logs/application.log
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```

### Step 4: Build the Project

```bash
# Navigate to project directory
cd ecommerce-multivendor

# Build with Maven
mvn clean install

# Skip tests during build (optional)
mvn clean install -DskipTests
```

### Step 5: Run the Application

```bash
# Using Maven
mvn spring-boot:run

# Or run the JAR directly
java -jar target/ecommerce-multivendor-0.0.1-SNAPSHOT.jar
```

**Success Message:**
```
Started EcommerceMultivendorApplication in X.XXX seconds
```

The API will be available at: `http://localhost:5454/api`

### Step 6: Verify Installation

Test the API with a simple health check:

```bash
curl -X GET http://localhost:5454/api/auth/health
```

## 📁 Project Structure

```
ecommerce-multivendor/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/a2z/
│   │   │       ├── EcommerceMultivendorApplication.java    # Main application entry point
│   │   │       ├── config/                                 # Configuration classes
│   │   │       │   ├── AppConfig.java                     # Spring beans configuration
│   │   │       │   ├── JWT_CONSTANT.java                  # JWT configuration constants
│   │   │       │   ├── JwtProvider.java                   # JWT token generation and validation
│   │   │       │   └── JwtTokenValidator.java             # JWT filter for request validation
│   │   │       ├── controller/                            # REST API controllers
│   │   │       │   ├── AuthController.java                # Authentication endpoints
│   │   │       │   ├── UserController.java                # User management endpoints
│   │   │       │   ├── SellerController.java              # Seller management endpoints
│   │   │       │   └── Home.java                          # Home page endpoints
│   │   │       ├── model/                                 # JPA Entity classes
│   │   │       │   ├── User.java                          # User entity
│   │   │       │   ├── Seller.java                        # Seller entity
│   │   │       │   ├── Product.java                       # Product entity
│   │   │       │   ├── Category.java                      # Category entity
│   │   │       │   ├── Cart.java                          # Shopping cart entity
│   │   │       │   ├── CartItem.java                      # Cart items
│   │   │       │   ├── Order.java                         # Order entity
│   │   │       │   ├── OrderItem.java                     # Order items
│   │   │       │   ├── Review.java                        # Product reviews
│   │   │       │   ├── Address.java                       # User/Seller addresses
│   │   │       │   ├── WishList.java                      # User wishlist
│   │   │       │   ├── Coupon.java                        # Discount coupons
│   │   │       │   ├── PaymentOrder.java                  # Payment orders
│   │   │       │   ├── VerificationCode.java              # OTP verification codes
│   │   │       │   ├── BussinessDetails.java              # Seller business info
│   │   │       │   ├── BankDetails.java                   # Seller bank info
│   │   │       │   └── ...
│   │   │       ├── repository/                            # Spring Data JPA repositories
│   │   │       │   ├── UserRepository.java
│   │   │       │   ├── SellerRepository.java
│   │   │       │   ├── ProductRepository.java
│   │   │       │   ├── CartRepository.java
│   │   │       │   ├── OrderRepository.java
│   │   │       │   └── ...
│   │   │       ├── service/                               # Business logic interfaces
│   │   │       │   ├── AuthService.java
│   │   │       │   ├── UserService.java
│   │   │       │   ├── SellerService.java
│   │   │       │   ├── ProductService.java
│   │   │       │   ├── EmailService.java
│   │   │       │   └── impl/                              # Service implementations
│   │   │       │       ├── AuthServiceImpl.java
│   │   │       │       ├── UserServiceImpl.java
│   │   │       │       ├── SellerServiceImpl.java
│   │   │       │       ├── CustomUserServiceImpl.java
│   │   │       │       └── EmailServiceImpl.java
│   │   │       ├── request/                               # Request DTOs
│   │   │       │   ├── LoginRequest.java
│   │   │       │   ├── LoginOtpRequest.java
│   │   │       │   ├── SignupRequest.java
│   │   │       │   └── ...
│   │   │       ├── response/                              # Response DTOs
│   │   │       │   ├── ApiResponse.java
│   │   │       │   ├── AuthResponse.java
│   │   │       │   └── ...
│   │   │       ├── exception/                             # Custom exceptions & handlers
│   │   │       │   ├── GlobalException.java               # Global exception handler
│   │   │       │   ├── ErrorDetails.java                  # Error response structure
│   │   │       │   ├── SellerException.java
│   │   │       │   └── ...
│   │   │       ├── enums/                                 # Enumeration classes
│   │   │       │   ├── USER_ROLE.java                     # User roles (BUYER, SELLER, ADMIN)
│   │   │       │   ├── AccountStatus.java                 # Account approval status
│   │   │       │   ├── OrderStatus.java                   # Order statuses
│   │   │       │   ├── PaymentStatus.java                 # Payment statuses
│   │   │       │   └── ...
│   │   │       └── utils/                                 # Utility classes
│   │   │           ├── OtpUtil.java                       # OTP generation utility
│   │   │           └── ...
│   │   └── resources/
│   │       ├── application.properties                     # Main configuration
│   │       ├── application-dev.properties                 # Development config
│   │       ├── application-prod.properties                # Production config
│   │       ├── static/                                    # Static resources
│   │       └── templates/                                 # Email templates
│   └── test/
│       └── java/com/a2z/                                  # Unit and integration tests
├── pom.xml                                                # Maven dependencies
├── mvnw & mvnw.cmd                                        # Maven wrapper
├── README.md                                              # This file
└── HELP.md                                                # Additional help
```

## ⚙️ Configuration Guide

### 1. JWT Configuration

Edit `src/main/java/com/a2z/config/JWT_CONSTANT.java`:

```java
public class JWT_CONSTANT {
    /**
     * JWT Secret Key (minimum 256 bits for HS256)
     * Generate a strong secret: openssl rand -base64 32
     */
    public static final String SECRET_KEY = "your_super_secret_key_at_least_256_bits_long_here!!!";
    
    /**
     * JWT Token expiration time in milliseconds (24 hours)
     */
    public static final long EXPIRATION_TIME = 86400000;
    
    /**
     * Refresh token expiration (7 days)
     */
    public static final long REFRESH_EXPIRATION = 604800000;
}
```

**Generate a strong secret key:**
```bash
# Linux/Mac
openssl rand -base64 32

# Windows
# Use online tools or Java code:
java -e "System.out.println(java.util.Base64.getEncoder().encodeToString(new java.security.SecureRandom().generateSeed(32)))"
```

### 2. Email Configuration

#### For Gmail:
1. Enable 2-Factor Authentication
2. Generate App Password: https://myaccount.google.com/apppasswords
3. Update `application.properties`:

```properties
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password  # 16-character password from Google
```

#### For Other Providers:
```properties
# SendGrid
spring.mail.host=smtp.sendgrid.net
spring.mail.port=587
spring.mail.username=apikey
spring.mail.password=SG.xxx...

# Custom SMTP
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your_username
spring.mail.password=your_password
```

### 3. Database Configuration

```properties
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/a2z?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# For PostgreSQL (alternative)
spring.datasource.url=jdbc:postgresql://localhost:5432/a2z
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 4. Logging Configuration

```properties
# Console logging
logging.level.root=INFO
logging.level.com.a2z=DEBUG
logging.pattern.console=%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n

# File logging
logging.file.name=logs/application.log
logging.file.max-size=10MB
logging.file.max-history=30
```

## 🔌 API Endpoints

### Authentication Endpoints

#### 1. Send OTP for Login/Signup
```http
POST /api/auth/sent/login-signup-otp
Content-Type: application/json

{
  "email": "user@example.com",
  "role": "BUYER"
}
```

**Response (200):**
```json
{
  "success": true,
  "message": "OTP sent to email successfully"
}
```

#### 2. Verify OTP and Login
```http
POST /api/auth/verify-otp
Content-Type: application/json

{
  "email": "user@example.com",
  "otp": "123456",
  "role": "BUYER"
}
```

**Response (200):**
```json
{
  "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "BUYER",
  "message": "Login successful"
}
```

#### 3. User Registration
```http
POST /api/auth/signup
Content-Type: application/json

{
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "otp": "123456"
}
```

**Response (200):**
```json
{
  "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "CUSTOMER",
  "message": "register successfully"
}
```

### User Endpoints

#### 1. Get User Profile
```http
GET /api/users/profile
Authorization: Bearer <jwt_token>
```

**Response (200):**
```json
{
  "id": 1,
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "mobile": "1234567890",
  "role": "CUSTOMER"
}
```

#### 2. Update User Profile
```http
PUT /api/users/profile
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Updated",
  "mobile": "9876543210"
}
```

### Seller Endpoints

#### 1. Register Seller
```http
POST /api/sellers/register
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "name": "Shop Name",
  "mobile": "9876543210",
  "businessDetails": {
    "businessName": "ABC Pvt Ltd",
    "businessType": "Electronics"
  },
  "bankDetails": {
    "accountNumber": "1234567890",
    "bankName": "ABC Bank"
  }
}
```

#### 2. Get Seller Profile
```http
GET /api/sellers/profile
Authorization: Bearer <jwt_token>
```

#### 3. Get All Sellers (Admin)
```http
GET /api/sellers
Authorization: Bearer <admin_jwt_token>
```

### Product Endpoints

#### 1. Get All Products
```http
GET /api/products?page=0&size=10&sort=name,asc
```

#### 2. Get Product by ID
```http
GET /api/products/{productId}
```

#### 3. Search Products
```http
GET /api/products/search?query=laptop&category=Electronics
```

#### 4. Create Product (Seller)
```http
POST /api/products
Authorization: Bearer <seller_jwt_token>
Content-Type: application/json

{
  "name": "Laptop Pro",
  "description": "High-performance laptop",
  "price": 999.99,
  "category": "Electronics",
  "images": ["url1", "url2"],
  "stock": 50
}
```

#### 5. Update Product (Seller)
```http
PUT /api/products/{productId}
Authorization: Bearer <seller_jwt_token>
Content-Type: application/json

{
  "name": "Updated Product",
  "price": 1099.99,
  "stock": 45
}
```

#### 6. Delete Product (Seller)
```http
DELETE /api/products/{productId}
Authorization: Bearer <seller_jwt_token>
```

### Cart Endpoints

#### 1. Add to Cart
```http
POST /api/cart/add
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "productId": 1,
  "quantity": 2
}
```

#### 2. View Cart
```http
GET /api/cart
Authorization: Bearer <jwt_token>
```

#### 3. Remove from Cart
```http
DELETE /api/cart/remove/{cartItemId}
Authorization: Bearer <jwt_token>
```

### Order Endpoints

#### 1. Place Order
```http
POST /api/orders
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "addressId": 1,
  "paymentMethod": "CREDIT_CARD"
}
```

#### 2. Get Order History
```http
GET /api/orders
Authorization: Bearer <jwt_token>
```

#### 3. Get Order Details
```http
GET /api/orders/{orderId}
Authorization: Bearer <jwt_token>
```

#### 4. Cancel Order
```http
PUT /api/orders/{orderId}/cancel
Authorization: Bearer <jwt_token>
```

## 🗄️ Database Schema

### Core Tables

#### users Table
```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  mobile VARCHAR(20),
  role ENUM('BUYER', 'SELLER', 'ADMIN') DEFAULT 'BUYER',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY idx_email (email),
  KEY idx_role (role)
);
```

#### sellers Table
```sql
CREATE TABLE seller (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL UNIQUE,
  name VARCHAR(255),
  password VARCHAR(255),
  mobile VARCHAR(20),
  gstin VARCHAR(50),
  is_email_verified BOOLEAN DEFAULT FALSE,
  account_status ENUM('PENDING', 'APPROVED', 'REJECTED', 'SUSPENDED') DEFAULT 'PENDING',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT uq_seller_email UNIQUE (email),
  KEY idx_email (email),
  KEY idx_status (account_status)
);
```

#### products Table
```sql
CREATE TABLE product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  seller_id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  price DECIMAL(12, 2) NOT NULL,
  mrp DECIMAL(12, 2),
  category VARCHAR(100),
  stock INT DEFAULT 0,
  images JSON,
  ratings DECIMAL(3, 2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE,
  KEY idx_seller (seller_id),
  KEY idx_category (category),
  KEY idx_price (price)
);
```

#### orders Table
```sql
CREATE TABLE orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  total_price DECIMAL(12, 2),
  order_status ENUM('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED') DEFAULT 'PENDING',
  payment_status ENUM('PENDING', 'COMPLETED', 'FAILED') DEFAULT 'PENDING',
  delivery_address_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  KEY idx_user (user_id),
  KEY idx_status (order_status)
);
```

#### cart Table
```sql
CREATE TABLE cart (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL UNIQUE,
  total_price DECIMAL(12, 2) DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

#### verification_code Table
```sql
CREATE TABLE verification_code (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  otp VARCHAR(6) NOT NULL,
  expiry_time TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  KEY idx_email (email)
);
```

## 🔐 Authentication & Security

### JWT Flow

```
Client Request
         ↓
    Includes JWT in Authorization Header
         ↓
    JwtTokenValidator Filter
         ↓
    Validate Token Signature & Expiry
         ↓
    Extract Claims (userId, role, email)
         ↓
    Create Authentication Object
         ↓
    Set in SecurityContextHolder
         ↓
    Request Proceeds to Controller
         ↓
    Role-Based Authorization Check (@PreAuthorize)
         ↓
    Business Logic Execution
         ↓
    Response Returned
```

### Security Features

1. **JWT Token Security**
   - HS256 (HMAC with SHA-256) algorithm
   - Signed tokens with secret key
   - Token expiration (24 hours by default)
   - Refresh token mechanism

2. **Password Security**
   - BCrypt hashing with salt
   - No plain text passwords stored
   - Password never exposed in API responses

3. **OTP Verification**
   - 6-digit OTP generation
   - Time-limited validity
   - One-time use per email
   - Email-based transmission

4. **Role-Based Access Control**
   ```java
   @PreAuthorize("hasRole('SELLER')")
   public ResponseEntity<?> createProduct(...) { }
   
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<?> approveSellerApplication(...) { }
   ```

### JWT Token Structure

```json
Header:
{
  "alg": "HS256",
  "typ": "JWT"
}

Payload:
{
  "userId": 1,
  "email": "user@example.com",
  "role": "BUYER",
  "iat": 1701859200,
  "exp": 1701945600
}

Signature:
HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), SECRET_KEY)
```

## 🐛 Known Issues & Solutions

### Issue 1: Duplicate Email - NonUniqueResultException

**Error Message:**
```
org.springframework.dao.IncorrectResultSizeDataAccessException: 
Query did not return a unique result: 2 results were returned
```

**Root Cause:**
- Multiple seller/user records with the same email exist in database
- No unique constraint on email column

**Quick Fix (Temporary Workaround):**

Edit `SellerRepository.java`:
```java
public interface SellerRepository extends JpaRepository<Seller, Long> {
    List<Seller> findAllByEmail(String email);  // Changed from Seller to List<Seller>
}
```

Edit `AuthServiceImpl.java` - `sendLoginOtp` method:
```java
@Override
public void sendLoginOtp(String email, USER_ROLE role) throws Exception {
    List<Seller> sellers = sellerRepository.findAllByEmail(email);
    
    if (sellers.isEmpty()) {
        throw new Exception("Seller not found with email: " + email);
    }
    
    if (sellers.size() > 1) {
        log.warn("Multiple sellers exist for email: {}. Using first match.", email);
    }
    
    Seller seller = sellers.get(0);
    // ... rest of code
}
```

**Permanent Fix (Recommended):**

1. **Find and remove duplicate records:**
```sql
-- Find duplicates
SELECT email, COUNT(*) AS cnt
FROM seller
GROUP BY LOWER(TRIM(email))
HAVING COUNT(*) > 1;

-- Show duplicate records
SELECT id, email, created_at
FROM seller
WHERE LOWER(TRIM(email)) IN (
  SELECT LOWER(TRIM(email))
  FROM seller
  GROUP BY LOWER(TRIM(email))
  HAVING COUNT(*) > 1
)
ORDER BY email, id;

-- Delete duplicates (keep lowest ID)
DELETE s1 FROM seller s1
INNER JOIN seller s2
  ON LOWER(TRIM(s1.email)) = LOWER(TRIM(s2.email))
  AND s1.id > s2.id;
```

2. **Add unique constraint to database:**
```sql
ALTER TABLE seller ADD CONSTRAINT uq_seller_email UNIQUE (email);
ALTER TABLE users ADD CONSTRAINT uq_user_email UNIQUE (email);
```

3. **Update Entity with @Column annotation:**
```java
@Entity
@Table(name = "seller", 
       uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Seller {
    
    @Column(nullable = false, unique = true)
    private String email;
    
    // Normalize email on save
    @PrePersist
    @PreUpdate
    public void normalizeEmail() {
        if (this.email != null) {
            this.email = this.email.trim().toLowerCase();
        }
    }
}
```

4. **Update Repository:**
```java
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByEmail(String email);
}
```

5. **Update Service to handle Optional:**
```java
public void sendLoginOtp(String email, USER_ROLE role) throws Exception {
    Optional<Seller> sellerOpt = sellerRepository.findByEmail(email);
    
    if (sellerOpt.isEmpty()) {
        throw new Exception("Seller not found");
    }
    
    Seller seller = sellerOpt.get();
    // ... rest of code
}
```

### Issue 2: Gmail SMTP Connection Fails

**Error:**
```
javax.mail.AuthenticationFailedException: 535 5.7.8 Username and password not accepted
```

**Solution:**
1. Enable 2-Factor Authentication: https://myaccount.google.com/security
2. Generate App Password: https://myaccount.google.com/apppasswords
3. Update `application.properties`:
   ```properties
   spring.mail.password=xzqe pvor hcth tcku  # Use the 16-char app password
   ```

### Issue 3: Lombok Symbol Not Found

**Error:**
```
java: cannot find symbol method builder()
```

**Solution:**
1. Ensure Lombok is installed in IDE:
   - IntelliJ IDEA: Settings → Plugins → Search "Lombok" → Install
   - Eclipse: Download from https://projectlombok.org/download

2. Enable annotation processing:
   - IntelliJ: Settings → Build, Execution, Deployment → Compiler → Annotation Processors → Enable

3. Clean and rebuild:
   ```bash
   mvn clean compile
   ```

### Issue 4: CORS Errors from Frontend

**Error:**
```
Cross-Origin Request Blocked
```

**Solution:**

Update `AppConfig.java`:
```java
@Configuration
public class AppConfig {
    
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
```

### Issue 5: Port 5454 Already in Use

**Error:**
```
Port 5454 is already in use
```

**Solution:**

Option 1: Change port in `application.properties`:
```properties
server.port=5455
```

Option 2: Kill process on port:
```bash
# Windows
netstat -ano | findstr :5454
taskkill /PID <PID> /F

# Mac/Linux
lsof -i :5454
kill -9 <PID>
```

## 💻 Development Workflow

### Code Style Guide

Follow Google Java Style Guide:
- **Indentation**: 4 spaces
- **Line length**: Max 100 characters
- **Naming**: camelCase for variables/methods, PascalCase for classes
- **Comments**: JavaDoc for public methods

### Branch Naming Convention

```
feature/description        # New feature
bugfix/description         # Bug fix
hotfix/description         # Critical hotfix
refactor/description       # Code refactoring
docs/description          # Documentation
```

### Commit Message Convention

```
<type>(<scope>): <subject>

<body>

<footer>

Types: feat, fix, docs, style, refactor, test, chore
Example: feat(auth): add OTP verification for login
```

### Local Development Setup

```bash
# 1. Clone repository
git clone https://github.com/chetanandmeher/a2z-ecommerce.git
cd ecommerce-multivendor

# 2. Create feature branch
git checkout -b feature/your-feature-name

# 3. Make changes and commit
git add .
git commit -m "feat(module): description"

# 4. Push to remote
git push origin feature/your-feature-name

# 5. Create pull request on GitHub
```

## 🚢 Deployment Guide

### Docker Deployment

#### Step 1: Create Dockerfile
```dockerfile
FROM openjdk:17-slim

WORKDIR /app

COPY target/ecommerce-multivendor-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 5454
```

#### Step 2: Build Docker Image
```bash
docker build -t a2z-ecommerce:1.0 .
```

#### Step 3: Create docker-compose.yml
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: a2z
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  app:
    build: .
    ports:
      - "5454:5454"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/a2z
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - mysql

volumes:
  mysql-data:
```

#### Step 4: Run Containers
```bash
docker-compose up -d
```

### Production Configuration

Create `application-prod.properties`:

```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Database
spring.datasource.url=jdbc:mysql://prod-db-host:3306/a2z
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate

# Logging
logging.level.root=WARN
logging.level.com.a2z=INFO
logging.file.name=/var/log/a2z/application.log

# Security
server.ssl.key-store=${SSL_KEYSTORE_PATH}
server.ssl.key-store-password=${SSL_KEYSTORE_PASSWORD}
```

### AWS Deployment

```bash
# 1. Build WAR/JAR
mvn clean package

# 2. Upload to Elastic Beanstalk
eb create a2z-prod

# 3. Deploy
eb deploy
```

## ✅ Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=AuthServiceImplTest
```

### Run with Coverage
```bash
mvn jacoco:report
```

View coverage report at `target/site/jacoco/index.html`

### Test Coverage Goals
- Overall: > 75%
- Service layer: > 85%
- Controller layer: > 70%

## 🔍 Troubleshooting

| Issue | Cause | Solution |
|-------|-------|----------|
| Application won't start | MySQL not running | Start MySQL service: `mysql.server start` |
| 403 Forbidden on protected endpoints | Invalid/expired JWT | Get new token from `/auth/sent/login-signup-otp` |
| Email not sending | SMTP credentials wrong | Verify Gmail app password in properties |
| Database migration fails | Wrong schema | Run `mvn flyway:repair` then `mvn flyway:migrate` |
| Lombok errors | Not installed in IDE | Install Lombok plugin in IDE |
| CORS errors | Frontend origin not whitelisted | Add origin to `addCorsMappings` in AppConfig |
| Out of memory | Heap size too small | Increase: `export JAVA_OPTS=-Xmx512m` |

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Docs](https://spring.io/projects/spring-security)
- [JWT Introduction](https://jwt.io/introduction)
- [REST API Best Practices](https://restfulapi.net/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Lombok Docs](https://projectlombok.org/)

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Contribution Guidelines

- Follow the code style guide
- Add unit tests for new features
- Update documentation
- Ensure all tests pass
- Provide clear commit messages

### Reporting Issues

1. Check if issue already exists
2. Provide detailed description
3. Include error messages and stack traces
4. Mention your environment (OS, Java version, etc.)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Chetanand Meher**
- GitHub: [@chetanandmeher](https://github.com/chetanandmeher)
- Email: camechoonewbeginning@gmail.com

## 🙏 Acknowledgments

- Spring Boot team for excellent framework
- Lombok team for code generation
- The open-source community for libraries and tools

---

## 📞 Support

For questions, issues, or suggestions:
- Create an issue on GitHub
- Send email to the author
- Check documentation in the `/docs` folder

**Last Updated**: December 2025  
**Version**: 1.0.0  
**Status**: Active Development ✅

---

Made with ❤️ by Chetanand Meher

