


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
- ✅ Product catalog management (CRUD operations via SellerProductController)
  - ✅ Create new products with hierarchical categories
  - ✅ View all seller products
  - ✅ Update product details (title, pricing, variants)
  - ✅ Delete products from catalog
- ✅ Inventory/Stock management
- ✅ Sales reports and analytics (via SellerReport)
  - ✅ Total earnings tracking
  - ✅ Total sales count
  - ✅ Refund tracking
  - ✅ Tax calculation
  - ✅ Net earning calculation
  - ✅ Order statistics (completed & cancelled)
  - ✅ Transaction count tracking
- ✅ Financial transaction tracking (via Transaction model)
- ✅ Multiple product categories (3-level hierarchy)
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
- ✅ Home page management (Home & HomeCategory)
  - ✅ Custom banner management
  - ✅ Featured product display
  - ✅ Category section customization
- ✅ Deals and promotions (Deal model)
- ✅ Multi-category support (3-level hierarchy)
- ✅ Order status tracking (Pending, Confirmed, Shipped, Delivered, Cancelled)
- ✅ Payment status management with Razorpay integration
- ✅ Financial transaction tracking and reporting
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
│   │   │       │   ├── SellerProductController.java       # Seller product CRUD endpoints
│   │   │       │   ├── ProductController.java             # Product search & filter endpoints
│   │   │       │   └── Home.java                          # Home page endpoints
│   │   │       ├── model/                                 # JPA Entity classes
│   │   │       │   ├── User.java                          # User entity
│   │   │       │   ├── Seller.java                        # Seller entity
│   │   │       │   ├── SellerReport.java                  # Seller financial reports & analytics
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
│   │   │       │   ├── Deal.java                          # Special deals & promotions
│   │   │       │   ├── Home.java                          # Home page configuration
│   │   │       │   ├── HomeCategory.java                  # Home page category display
│   │   │       │   ├── PaymentOrder.java                  # Payment orders
│   │   │       │   ├── PaymentDetails.java                # Payment transaction details
│   │   │       │   ├── Transaction.java                   # Financial transaction tracking
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
### Seller Product Management Endpoints

#### 1. Get All Seller Products
```http
GET /api/seller/products
Authorization: Bearer <seller_jwt_token>
```

**Response (200):**
```json
[
  {
    "id": 1,
    "title": "Laptop Pro 15",
    "description": "High-performance laptop",
    "mrpPrice": 1299,
    "sellingPrice": 999,
    "discountPercentage": 23,
    "color": "Black",
    "size": "15inch",
```

### Seller Product Management Endpoints

#### 1. Get All Seller Products
```http
GET /api/seller/products
Authorization: Bearer <seller_jwt_token>
```

**Response (200):**
```json
[
  {
    "id": 1,
    "title": "Laptop Pro 15",
### Product Endpoints

#### 1. Get All Products with Advanced Filtering
```http
GET /api/products?category=electronics&colors=black&sizes=M&minPrice=100&maxPrice=1000&sort=price_low&minDiscountPercentage=10&pageNumber=0
```

**Query Parameters:**
- `category` - Filter by category ID (Level 3 category)
- `colors` - Filter by product color
- `sizes` - Filter by product size
- `minPrice` - Minimum selling price filter
- `maxPrice` - Maximum selling price filter
- `sort` - Sort options: `price_low`, `price_high`
- `minDiscountPercentage` - Minimum discount percentage
- `stock` - Filter by stock availability
- `pageNumber` - Page number (0-indexed)

**Response (200):**
```json
{
  "content": [
    {
      "id": 1,
      "title": "Laptop Pro 15",
      "description": "High-performance laptop",
      "mrpPrice": 1299,
      "sellingPrice": 999,
      "discountPercentage": 23,
      "color": "Black",
      "size": "15inch",
      "images": ["url1", "url2"],
      "category": { "id": 1, "categoryId": "electronics" },
      "seller": { "id": 1, "name": "TechStore" },
      "createdAt": "2025-12-07T10:30:00"
    }
  ],
  "totalPages": 5,
  "totalElements": 45,
  "currentPage": 0
}
```

**Query Parameters:**
- `category` - Filter by category ID (Level 3 category)
- `colors` - Filter by product color
- `sizes` - Filter by product size
- `minPrice` - Minimum selling price filter
**Response (200):**
```json
{
  "id": 1,
  "title": "Laptop Pro 15",
  "description": "High-performance laptop with 16GB RAM",
  "mrpPrice": 1299,
  "sellingPrice": 999,
  "discountPercentage": 23,
  "quantity": 50,
  "color": "Black",
  "size": "15inch",
  "images": ["url1", "url2"],
  "numberOfReviews": 15,
  "category": { "id": 1, "categoryId": "laptops", "level": 3 },
  "seller": { "id": 1, "name": "TechStore" },
  "reviews": [],
  "createdAt": "2025-12-07T10:30:00"
}
```

- `maxPrice` - Maximum selling price filter
- `sort` - Sort options: `price_low`, `price_high`
GET /api/products/search?query=laptop
```

**Response (200):**
```json
[
  {
    "id": 1,
    "title": "Laptop Pro 15",
    "sellingPrice": 999,
    "discountPercentage": 23
  },
  {
    "id": 2,
    "title": "Laptop Gaming 17",
    "sellingPrice": 1499,
    "discountPercentage": 30
  }
]
- `stock` - Filter by stock availability
- `pageNumber` - Page number (0-indexed)

**Response (200):**
```json
{
  "content": [
    {
      "id": 1,
  "title": "Laptop Pro 15",
  "description": "High-performance laptop with 16GB RAM and 512GB SSD",
  "mrpPrice": 1299,
  "sellingPrice": 999,
  "color": "Black",
  "size": "15inch",
  "images": ["url1", "url2"],
  "category1": "electronics",
  "category2": "computers",
  "category3": "laptops"
}
```

**Note:** The system supports hierarchical categories with 3 levels. If categories don't exist, they are created automatically.

**Response (201):**
```json
{
  "id": 1,
  "title": "Laptop Pro 15",
  "description": "High-performance laptop with 16GB RAM and 512GB SSD",
  "mrpPrice": 1299,
  "sellingPrice": 999,
  "discountPercentage": 23,
  "color": "Black",
  "size": "15inch",
      "discountPercentage": 23,
  "category": { "id": 1, "categoryId": "laptops", "level": 3 },
  "seller": { "id": 1, "name": "TechStore" },
  "createdAt": "2025-12-07T10:30:00"
      "size": "15inch",
      "images": ["url1", "url2"],
      "category": { "id": 1, "categoryId": "electronics" },
      "seller": { "id": 1, "name": "TechStore" },
      "createdAt": "2025-12-07T10:30:00"
    }
  ],
  "totalPages": 5,
  "totalElements": 45,
  "currentPage": 0
  "title": "Updated Laptop Pro 15",
  "sellingPrice": 1099,
  "mrpPrice": 1399,
  "color": "Silver",
  "quantity": 45
}
```

**Response (200):**
```json
#### 2. Get Product by ID
```http
GET /api/products/{productId}
```

**Response (200):**
```json
{
  "id": 1,
  "title": "Laptop Pro 15",
  "description": "High-performance laptop with 16GB RAM",
  "mrpPrice": 1299,
  "sellingPrice": 999,
  "discountPercentage": 23,
  "quantity": 50,
  "color": "Black",
  "size": "15inch",
  "images": ["url1", "url2"],
  "numberOfReviews": 15,
  "category": { "id": 1, "categoryId": "laptops", "level": 3 },
  "seller": { "id": 1, "name": "TechStore" },
  "reviews": [],
  "createdAt": "2025-12-07T10:30:00"
}
```

#### 3. Search Products
```http
GET /api/products/search?query=laptop
```

**Response (200):**
```json
[
  {
    "id": 1,
    "title": "Laptop Pro 15",
    "sellingPrice": 999,
    "discountPercentage": 23
  },
  {
    "id": 2,
    "title": "Laptop Gaming 17",
    "sellingPrice": 1499,
    "discountPercentage": 30
  }
]
```

#### 4. Create Product (Seller)
```http
POST /api/products
Authorization: Bearer <seller_jwt_token>
Content-Type: application/json

{
  "title": "Laptop Pro 15",
  "description": "High-performance laptop with 16GB RAM and 512GB SSD",
  "mrpPrice": 1299,
  "sellingPrice": 999,
  "color": "Black",
  "size": "15inch",
  "images": ["url1", "url2"],
  "category1": "electronics",
  "category2": "computers",
  "category3": "laptops"
}
```

**Note:** The system supports hierarchical categories with 3 levels. If categories don't exist, they are created automatically.

**Response (201):**
```json
{
  "id": 1,
  "title": "Laptop Pro 15",
  "description": "High-performance laptop with 16GB RAM and 512GB SSD",
  "mrpPrice": 1299,
  "sellingPrice": 999,
  "discountPercentage": 23,
  "color": "Black",
  "size": "15inch",
  "images": ["url1", "url2"],
  "category": { "id": 1, "categoryId": "laptops", "level": 3 },
  "seller": { "id": 1, "name": "TechStore" },
  "createdAt": "2025-12-07T10:30:00"
}
```

#### 5. Update Product (Seller)
```http
PUT /api/products/{productId}
Authorization: Bearer <seller_jwt_token>
Content-Type: application/json

{
  "title": "Updated Laptop Pro 15",
  "sellingPrice": 1099,
  "mrpPrice": 1399,
  "color": "Silver",
  "quantity": 45
}
```

**Response (200):**
```json
}
```

#### 6. Delete Product (Seller)
```http
  title VARCHAR(255) NOT NULL,
```
  mrp_price INT NOT NULL,
  selling_price INT NOT NULL,
  discount_percentage INT DEFAULT 0,
  quantity INT DEFAULT 0,
  color VARCHAR(100),
  size VARCHAR(50),
  number_of_reviews INT DEFAULT 0,
  category_id BIGINT NOT NULL,
  seller_id BIGINT NOT NULL,
Authorization: Bearer <jwt_token>
Content-Type: application/json
  FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE RESTRICT,

{
  KEY idx_category (category_id),
  KEY idx_selling_price (selling_price),
  KEY idx_discount (discount_percentage)
);
```

#### category Table (Hierarchical Categories - 3 Levels)
```sql
CREATE TABLE category (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  category_id VARCHAR(100) NOT NULL,
  level INT NOT NULL,
  parent_category_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (parent_category_id) REFERENCES category(id) ON DELETE CASCADE,
  UNIQUE KEY uq_category_level (category_id, level),
  KEY idx_parent (parent_category_id)
);
```

**Category Hierarchy Example:**
```
Level 1 (Main): electronics
  └─ Level 2 (Sub): computers
      └─ Level 3 (Product Type): laptops
```

#### product_images Table
```sql
CREATE TABLE product_images (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  image_url VARCHAR(500),
  FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
  KEY idx_product (product_id)
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
## 📊 Service Layer Documentation

### ProductService Implementation

The ProductService provides comprehensive product management with advanced filtering, hierarchical category support, and automatic discount calculation.

#### Key Features

**1. Hierarchical Category System**
- Three-level category structure (Level 1: Main, Level 2: Sub, Level 3: Product Type)
- Automatic category creation if not exists
- Parent-child relationships maintained

```java
// Example Category Hierarchy
Level 1: electronics
└── Level 2: computers
    └── Level 3: laptops
```

**2. Discount Calculation**
```java
Discount % = ((MRP Price - Selling Price) / MRP Price) * 100
```

**Example:**
```
MRP Price: $1299
Selling Price: $999
Discount: ((1299 - 999) / 1299) * 100 = 23%
```

**3. Advanced Product Filtering**

The `getAllProducts()` method supports multiple filter parameters:

| Filter Parameter | Type | Description | Example |
|-----------------|------|-------------|---------|
| `category` | String | Level 3 category ID | "laptops" |
| `colors` | String | Product color | "black", "silver" |
| `sizes` | String | Product size | "M", "L", "15inch" |
| `minPrice` | Integer | Minimum selling price | 100 |
| `maxPrice` | Integer | Maximum selling price | 1000 |
| `sort` | String | Sort option: `price_low`, `price_high` | "price_low" |
| `minDiscountPercentage` | Integer | Minimum discount % | 10 |
| `stock` | String | Stock availability | "in_stock" |
| `pageNumber` | Integer | Page number (0-indexed) | 0 |

**Example Query:**
```bash
GET /api/products?category=laptops&colors=black&minPrice=500&maxPrice=1500&sort=price_low&minDiscountPercentage=15&pageNumber=0
```

**4. Product Search**

Full-text search across product title and description:

```java
List<Product> searchProduct(String query);
```

**5. Seller Product Management**

Retrieve all products for a specific seller:

```java
List<Product> getProductsBySellerId(Long sellerId);
```

#### Method Documentation

```java
/**
 * Creates a new product with hierarchical category support
 * 
 * @param request CreateProductRequest containing product details
 * @param seller The seller who owns the product
 * @return Created Product entity
 * @throws IllegalArgumentException if MRP price is invalid
 */
public Product createProduct(CreateProductRequest request, Seller seller)

/**
 * Retrieves all products with advanced filtering and pagination
 * 
 * @param category Level 3 category ID
 * @param colors Product color
 * @param sizes Product size
 * @param minPrice Minimum selling price
 * @param maxPrice Maximum selling price
 * @param sort Sorting option (price_low, price_high)
 * @param minDiscountPercentage Minimum discount percentage
 * @param stock Stock availability filter
 * @param pageNumber Page number (0-indexed)
 * @return Page<Product> with filtered results
 */
public Page<Product> getAllProducts(...)

/**
 * Deletes a product by ID
 * 
 * @param productId The product to delete
 * @throws ProductException if product not found
 */
public void deleteProduct(Long productId) throws ProductException

/**
 * Updates an existing product
 * 
 * @param productId The product ID to update
 * @param updatedProduct Updated product details
 * @return Updated Product entity
 * @throws ProductException if product not found
 */
public Product updateProduct(Long productId, Product updatedProduct) throws ProductException

/**
 * Finds a product by ID
 * 
 * @param productId The product ID
 * @return Product entity
 * @throws ProductException if product not found
 */
public Product findProductById(Long productId) throws ProductException

/**
 * Searches products by query string
 * 
 * @param query Search keyword
 * @return List of matching products
 */
public List<Product> searchProduct(String query)

/**
 * Retrieves all products for a specific seller
 * 
 * @param sellerId The seller ID
 * @return List of seller's products
 */
public List<Product> getProductsBySellerId(Long sellerId)
```

#### Implementation Details

**Category Creation Logic:**
```java
// If category doesn't exist, create it
Category category = categoryRepository.getByCategoryId(categoryId);
if(category == null) {
    category = Category.builder()
            .categoryId(categoryId)
            .level(level)
            .parentCategory(parentCategory)
            .build();
    categoryRepository.save(category);
}
```

**Filtering Using JPA Criteria API:**
```java
Specification<Product> spec = (root, query, criteriaBuilder) -> {
    List<Predicate> predicates = new ArrayList<>();
    
    // Add predicates based on provided filters
    if(category != null) {
        Join<Product, Category> categoryJoin = root.join("category");
        predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
    }
    
    if(minPrice != null) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
            root.get("sellingPrice"), minPrice
        ));
    }
    
    // ... more filters
    
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
};
```

**Sorting and Pagination:**
```java
Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("sellingPrice").ascending());
return productRepository.findAll(spec, pageable);
```

## 🛍️ Product Management Features

### Overview
The A2Z platform provides sellers with comprehensive product management capabilities including:

- **Multi-level Category Support**: Organize products in 3-level hierarchical categories
- **Dynamic Pricing**: Support for MRP and selling price with automatic discount calculation
- **Advanced Filtering**: Search and filter by multiple criteria
- **Image Management**: Store multiple product images
- **Review System**: Customer reviews and ratings
- **Inventory Management**: Track product quantity/stock
- **Product Variants**: Support for color and size variants

### Hierarchical Category Structure

The platform uses a 3-level category hierarchy for better product organization:

```
Level 1: Main Category
├─ Electronics
├─ Fashion
├─ Home & Garden
└─ Sports

Level 2: Sub Category
├─ Electronics
│  ├─ Smartphones
│  ├─ Laptops
│  └─ Tablets
│
└─ Fashion
   ├─ Men's Clothing
   ├─ Women's Clothing
   └─ Footwear

Level 3: Product Type
├─ Laptops
│  ├─ Gaming Laptops
│  ├─ Business Laptops
│  └─ Ultrabooks
│
└─ Smartphones
   ├─ iPhones
   ├─ Android Phones
   └─ Budget Phones
```

### Pricing Strategy

The platform automatically calculates discount percentage based on MRP and selling price:

```
Formula: Discount % = ((MRP Price - Selling Price) / MRP Price) × 100

Examples:
MRP: $1000, Selling Price: $800  → Discount: 20%
MRP: $500,  Selling Price: $399  → Discount: 20.2%
MRP: $100,  Selling Price: $99   → Discount: 1%
```

### Product Variants

Products support two types of variants:

1. **Size Variants**
   - Clothing: XS, S, M, L, XL, XXL
   - Electronics: Screen size (15inch, 17inch)
   - Storage: Capacity (128GB, 256GB, 512GB)

2. **Color Variants**
   - Black, White, Silver, Gold, Rose Gold, Blue, Red, Green, etc.

### Best Practices for Product Creation

1. **Title Formatting**
   - Keep titles concise but descriptive (50-80 characters)
   - Include key specifications (brand, model, key features)
   - Example: "iPhone 15 Pro 128GB Space Black" ✓
   - Avoid: "Best Phone Ever - Get Yours Now!!!" ✗

2. **Description Quality**
   - Highlight key features and specifications
   - Include dimensions, weight, material information
   - Mention warranty and after-sales service
   - Use bullet points for readability
   - Example:
     ```
     iPhone 15 Pro Specifications:
     • Display: 6.1-inch Super Retina XDR
     • Processor: A17 Pro chip
     • Camera: Dual 48MP + 12MP Ultra Wide
     • Battery: Up to 20 hours
```

## 📊 Seller Analytics & Reporting

### SellerReport Features

The SellerReport entity provides comprehensive financial tracking for sellers:

#### Key Metrics Tracked

| Metric | Description | Use Case |
|--------|-------------|----------|
| **Total Earnings** | Cumulative revenue from all sales | Financial reporting |
| **Total Sales** | Number of products sold | Performance tracking |
| **Total Refunds** | Amount refunded to customers | Refund management |
| **Total Tax** | Tax collected on sales | Tax compliance |
| **Net Earning** | Final amount after deductions | Profit calculation |
| **Total Orders** | Number of completed orders | Order statistics |
| **Cancelled Orders** | Count of cancelled transactions | Quality metrics |
| **Total Transactions** | Financial transaction count | Audit trail |

#### SellerReport Structure
```java
@Entity
public class SellerReport {
    private long id;
    private Seller seller;          // One-to-one relationship with seller
## 🗄️ Database Schema

### Core Tables

#### users Table
```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL UNIQUE,
    private Long totalEarnings;     // Revenue tracking
    private Long totalSales;        // Sales count
    private Long totalRefunds;      // Refund amount
    private Long totalTax;          // Tax tracking
    private Long netEarning;        // Final profit
    private Integer totalOrders;    // Completed orders
    private Integer cancelledOrders; // Cancelled orders
    private Integer totalTransactions; // Transaction count
}
```

## 🏠 Home Page Management

### Overview
The Home and HomeCategory entities enable dynamic configuration of the home page display without code changes.

#### Features

1. **Daily Best Selling Product**
   - Automatically feature the top-selling product
   - Dynamic updates based on sales data
   - Drives customer attention to popular items

2. **Daily Deal Product**
   - Special promotional product rotation
   - Time-based deal management
   - Encourages repeat visits

3. **Category Section Management**
   - Organize categories by section (TOP, MIDDLE, BOTTOM)
   - Multiple categories per section
   - Responsive layout support

#### Home Page Structure
```java
@Entity
public class Home {
    private long id;
    private Product dailyBestSellingProduct;  // Best seller
    private Product dailyDealProduct;         // Promotional product
    private List<HomeCategory> categories;    // Category displays
}

@Entity
public class HomeCategory {
    private long id;
    private Home home;
    private Category category;
    private HomeCategorySection section;     // TOP, MIDDLE, BOTTOM
}
```

#### Supported Sections
- **TOP**: Header section for premium visibility
- **MIDDLE**: Main content area
- **BOTTOM**: Footer section

## 💳 Payment Integration

### PaymentDetails Entity
The PaymentDetails model integrates with Razorpay for payment processing:

#### Supported Payment Methods
- Credit Card
- Debit Card
- Net Banking
- Wallet
- UPI

#### Payment Fields
```java
@Data
public class PaymentDetails {
    private String paymentId;                    // Internal payment ID
    private String razorpayPaymentLinkId;       // Razorpay link ID
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
  title VARCHAR(255) NOT NULL,
  description TEXT,
  mrp_price INT NOT NULL,
  selling_price INT NOT NULL,
  discount_percentage INT DEFAULT 0,
  quantity INT DEFAULT 0,
  color VARCHAR(100),
  size VARCHAR(50),
  number_of_reviews INT DEFAULT 0,
  category_id BIGINT NOT NULL,
  seller_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE RESTRICT,
  FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE,
  KEY idx_seller (seller_id),
  KEY idx_category (category_id),
  KEY idx_selling_price (selling_price),
  KEY idx_discount (discount_percentage)
);
```

#### category Table (Hierarchical Categories - 3 Levels)
```sql
CREATE TABLE category (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  category_id VARCHAR(100) NOT NULL,
  level INT NOT NULL,
  parent_category_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (parent_category_id) REFERENCES category(id) ON DELETE CASCADE,
  UNIQUE KEY uq_category_level (category_id, level),
  KEY idx_parent (parent_category_id)
);
```

**Category Hierarchy Example:**
```
Level 1 (Main): electronics
  └─ Level 2 (Sub): computers
      └─ Level 3 (Product Type): laptops
```

#### product_images Table
```sql
CREATE TABLE product_images (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  image_url VARCHAR(500),
  FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
  KEY idx_product (product_id)
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

#### seller_report Table (Analytics & Financial Reporting)
```sql
CREATE TABLE seller_report (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  seller_id BIGINT NOT NULL UNIQUE,
  total_earnings BIGINT DEFAULT 0,
  total_sales BIGINT DEFAULT 0,
  total_refunds BIGINT DEFAULT 0,
  total_tax BIGINT DEFAULT 0,
  net_earning BIGINT DEFAULT 0,
  total_orders INT DEFAULT 0,
  cancelled_orders INT DEFAULT 0,
  total_transactions INT DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE,
  KEY idx_seller (seller_id)
);
```

#### transaction Table (Financial Transaction Tracking)
```sql
CREATE TABLE transaction (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_id BIGINT NOT NULL,
  order_id BIGINT NOT NULL,
  seller_id BIGINT NOT NULL,
  date DATETIME DEFAULT CURRENT_TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE,
  KEY idx_customer (customer_id),
  KEY idx_seller (seller_id),
  KEY idx_date (date)
);
```

#### payment_details Table (Razorpay Integration)
```sql
CREATE TABLE payment_details (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  payment_id VARCHAR(255),
  razorpay_payment_link_id VARCHAR(255),
  razorpay_payment_link_reference_id VARCHAR(255),
  razorpay_payment_link_status VARCHAR(50),
  razorpay_payment_id_zwsp VARCHAR(255),
  status ENUM('PENDING', 'COMPLETED', 'FAILED') DEFAULT 'PENDING',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  KEY idx_payment_id (payment_id),
  KEY idx_status (status)
);
```

#### deal Table (Promotions & Special Offers)
```sql
CREATE TABLE deal (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  discount_percent INT DEFAULT 0,
  category_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE,
  KEY idx_category (category_id)
);
```

#### home Table (Home Page Configuration)
```sql
CREATE TABLE home (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  daily_best_selling_product_id BIGINT,
  daily_deal_product_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (daily_best_selling_product_id) REFERENCES product(id) ON DELETE SET NULL,
  FOREIGN KEY (daily_deal_product_id) REFERENCES product(id) ON DELETE SET NULL
);
```

#### home_category Table (Home Page Category Display)
```sql
CREATE TABLE home_category (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
## 📊 Service Layer Documentation

### ProductService Implementation

The ProductService provides comprehensive product management with advanced filtering, hierarchical category support, and automatic discount calculation.

#### Key Features

**1. Hierarchical Category System**
- Three-level category structure (Level 1: Main, Level 2: Sub, Level 3: Product Type)
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

## ⚡ Performance Optimization

### Database Indexing for Products

Optimize product queries with appropriate indexes:

```sql
-- Index on frequently filtered columns
CREATE INDEX idx_product_selling_price ON product(selling_price);
CREATE INDEX idx_product_discount ON product(discount_percentage);
CREATE INDEX idx_product_seller ON product(seller_id);
CREATE INDEX idx_product_category ON product(category_id);

-- Composite indexes for common filter combinations
CREATE INDEX idx_product_category_price ON product(category_id, selling_price);
CREATE INDEX idx_product_seller_created ON product(seller_id, created_at DESC);

-- Index for search operations
CREATE FULLTEXT INDEX idx_product_search ON product(title, description);
```

### Query Optimization Tips

1. **Use Pagination**
   - Always paginate large result sets
   - Default page size: 10 items
   - Reduces memory usage and improves response time

2. **Avoid N+1 Query Problem**
   ```java
   // ✓ Good: Use join fetch
   findAll(spec, pageable)  // JPA handles joins efficiently
   
   // ✗ Bad: Separate queries for each product's seller
   for(Product p : products) {
       Seller s = sellerRepository.findById(p.getSellerId());
   }
   ```

3. **Cache Frequently Accessed Data**
   ```java
   @Cacheable("products")
   public Product findProductById(Long id) {
       return productRepository.findById(id).orElseThrow();
   }
   ```

4. **Filter Early**
   - Apply filters at database level, not application level
   - Let Criteria API build optimal SQL

5. **Lazy Load Reviews**
   ```java
   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
   private List<Review> reviews;
   ```

### Response Time Benchmarks

| Operation | Expected Time | Conditions |
|-----------|---------------|-----------|
| Create Product | < 500ms | Average product with 2 images |
| Get Product by ID | < 50ms | With reviews lazy loaded |
| Search (10 results) | < 100ms | Full-text search, indexed |
| Filter (10 results) | < 150ms | Multiple filters applied |
| Get Seller Products | < 200ms | 50+ products, paginated |

### Caching Strategy

```properties
# Enable caching in application.properties
spring.cache.type=simple
spring.cache.cache-names=products,categories,sellers

# Or use Redis for distributed cache
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

## ⚡ Performance Optimization
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

## 🚀 Quick Reference Guide

### Common API Calls

#### 1. Send OTP for Login/Signup (Curl)
```bash
curl -X POST http://localhost:5454/api/auth/sent/login-signup-otp \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "role": "SELLER"
  }'
```

#### 2. Get All Seller Products (Curl)
```bash
curl -X GET http://localhost:5454/api/seller/products \
  -H "Authorization: Bearer <seller_jwt_token>" \
  -H "Content-Type: application/json"
```

#### 3. Create a Product (Curl)
```bash
curl -X POST http://localhost:5454/api/seller/products \
  -H "Authorization: Bearer <seller_jwt_token>" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "MacBook Pro 16\"",
    "description": "M3 Pro chip with 18GB RAM and 512GB SSD",
    "mrpPrice": 2499,
    "sellingPrice": 2199,
    "color": "Space Gray",
    "size": "16inch",
    "images": ["https://example.com/macbook-1.jpg"],
    "category1": "electronics",
    "category2": "computers",
    "category3": "laptops"
  }'
```

#### 4. Get Products with Filtering (Curl)
```bash
curl -X GET "http://localhost:5454/api/products?category=laptops&minPrice=500&maxPrice=2000&sort=price_low&pageNumber=0" \
  -H "Content-Type: application/json"
```

#### 5. Search Products (Curl)
```bash
curl -X GET "http://localhost:5454/api/products/search?query=laptop" \
  -H "Content-Type: application/json"
```

#### 6. Get Product by ID (Curl)
```bash
curl -X GET http://localhost:5454/api/products/1 \
  -H "Content-Type: application/json"
```

#### 7. Update Product (Curl)
```bash
curl -X PUT http://localhost:5454/api/seller/products/1 \
  -H "Authorization: Bearer <seller_jwt_token>" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated MacBook Pro",
    "sellingPrice": 2099,
    "quantity": 45
  }'
```

#### 8. Delete Product (Curl)
```bash
curl -X DELETE http://localhost:5454/api/seller/products/1 \
  -H "Authorization: Bearer <seller_jwt_token>" \
  -H "Content-Type: application/json"
```

### Using Postman

1. **Create New Collection**: "A2Z E-Commerce"
2. **Add Environment Variables**:
   - `base_url`: http://localhost:5454/api
   - `jwt_token`: (obtained from login endpoint)
   - `seller_id`: (your seller ID)

3. **Import Sample Requests**: Save the curl commands above as Postman requests

### Using Insomnia

1. Import workspace from URLs
2. Set up environment variables (same as Postman)
3. Create requests using the examples above

## 🚀 Quick Reference Guide

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

**Last Updated**: December 8, 2025  

For questions, issues, or suggestions:
- Create an issue on GitHub
- Send email to the author
- Check documentation in the `/docs` folder

**Last Updated**: December 2025  
**Version**: 1.0.0  
**Status**: Active Development ✅

---

Made with ❤️ by Chetanand Meher

