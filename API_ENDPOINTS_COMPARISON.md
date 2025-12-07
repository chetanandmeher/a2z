DELETE /api/seller/products/42
Authorization: Bearer <seller_jwt_token>

Response (200 OK):
(No content body)
```

## Category Hierarchy Examples

### Example 1: Electronics
```
Level 1: electronics
  └─ Level 2: computers
      └─ Level 3: laptops
```

### Example 2: Fashion
```
Level 1: fashion
  └─ Level 2: mens-clothing
      └─ Level 3: shirts
```

### Example 3: Mobile Phones
```
Level 1: electronics
  └─ Level 2: smartphones
      └─ Level 3: iphones
```

## Security & Authentication

### JWT Token Required
All SellerProductController endpoints require a valid JWT token in the Authorization header:

```bash
Authorization: Bearer <jwt_token>
```

### Token Validation
- Signature verification using HS256
- Expiration check (24 hours default)
- Role verification (SELLER role required)

### Example Response on Invalid Token
```json
{
  "timestamp": "2025-12-08T12:00:00.000+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid or expired JWT token",
  "path": "/api/seller/products"
}
```

## Error Handling

### Common Error Responses

#### 401 Unauthorized
```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "JWT token is missing or invalid"
}
```

#### 403 Forbidden
```json
{
  "status": 403,
  "error": "Forbidden",
  "message": "User does not have SELLER role"
}
```

#### 404 Not Found
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Product not found with ID: 999"
}
```

#### 400 Bad Request
```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Selling price cannot be greater than MRP price"
}
```

## Testing Guide

### Using curl

#### 1. Get Seller Token
```bash
curl -X POST http://localhost:5454/api/auth/sent/login-signup-otp \
  -H "Content-Type: application/json" \
  -d '{
    "email": "seller@example.com",
    "role": "SELLER"
  }'
```

#### 2. Verify OTP & Get JWT
```bash
curl -X POST http://localhost:5454/api/auth/verify-otp \
  -H "Content-Type: application/json" \
  -d '{
    "email": "seller@example.com",
    "otp": "123456",
    "role": "SELLER"
  }'
```

#### 3. Create Product
```bash
curl -X POST http://localhost:5454/api/seller/products \
  -H "Authorization: Bearer <jwt_from_step_2>" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test Product",
    "mrpPrice": 1000,
    "sellingPrice": 800,
    "color": "Black",
    "size": "M",
    "images": [],
    "category1": "electronics",
    "category2": "phones",
    "category3": "smartphones"
  }'
```

#### 4. List Products
```bash
curl -X GET http://localhost:5454/api/seller/products \
  -H "Authorization: Bearer <jwt_token>"
```

### Using Postman

1. Create a new collection: "A2Z E-Commerce Seller"
2. Set up environment variable: `seller_jwt = <your_token>`
3. Add requests:
   - GET `/api/seller/products`
   - POST `/api/seller/products`
   - PUT `/api/seller/products/1`
   - DELETE `/api/seller/products/1`
4. Use `{{seller_jwt}}` in Authorization header

## Summary of Changes

✅ **Added** - SellerProductController with 4 CRUD endpoints
✅ **Documented** - Complete API endpoint documentation
✅ **Clarified** - Distinction between buyer and seller APIs
✅ **Exemplified** - Request/response examples for all endpoints
✅ **Updated** - Project structure documentation
✅ **Enhanced** - Seller feature list with detailed operations
✅ **Provided** - Testing guide for all new endpoints

---

**Last Updated**: December 8, 2025
**Controller**: SellerProductController.java
**Base Path**: /api/seller/products
**Status**: Fully Documented ✅

