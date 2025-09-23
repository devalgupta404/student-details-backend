# E-Commerce CRUD API Documentation

## Overview

This is a college-specific e-commerce platform where users can list items for sale. Only users with valid college email domains can participate.

## Valid College Domains

- iit.ac.in
- nit.ac.in
- iiit.ac.in
- bits-pilani.ac.in
- du.ac.in
- jnu.ac.in
- iisc.ac.in
- iim.ac.in
- vit.ac.in
- manipal.edu
- amity.edu
- srmuniv.ac.in

## API Endpoints

### User Management

#### Create User

```
POST /api/users
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john.doe@iit.ac.in"
}
```

#### Get All Users

```
GET /api/users
```

#### Get User by Email

```
GET /api/users/{email}
```

#### Get Users by College

```
GET /api/users/college/{domain}
```

#### Validate College Email

```
GET /api/users/validate-email/{email}
```

### Item Management

#### Create Item

```
POST /api/items
Content-Type: application/json

{
    "itemName": "MacBook Pro",
    "price": 50000.0,
    "category": "Electronics",
    "description": "Used MacBook Pro in good condition",
    "imageUrl": "https://example.com/image.jpg",
    "ownerEmail": "john.doe@iit.ac.in"
}
```

#### Get All Items

```
GET /api/items
```

#### Get Items by College

```
GET /api/items/college/{collegeDomain}
```

#### Get Items by Category

```
GET /api/items/category/{category}
```

#### Get Items by College and Category

```
GET /api/items/college/{collegeDomain}/category/{category}
```

#### Get Item by ID

```
GET /api/items/{id}
```

#### Update Item

```
PUT /api/items/{id}
Content-Type: application/json

{
    "itemName": "Updated MacBook Pro",
    "price": 45000.0,
    "category": "Electronics",
    "description": "Updated description",
    "imageUrl": "https://example.com/new-image.jpg",
    "ownerEmail": "john.doe@iit.ac.in"
}
```

#### Delete Item

```
DELETE /api/items/{id}
```

#### Get User's Items

```
GET /api/items/user/{userEmail}
```

## Example Usage

### 1. Create a User

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Smith",
    "email": "alice.smith@iit.ac.in"
  }'
```

### 2. Create an Item

```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "itemName": "iPhone 13",
    "price": 35000.0,
    "category": "Electronics",
    "description": "iPhone 13 in excellent condition",
    "imageUrl": "https://example.com/iphone.jpg",
    "ownerEmail": "alice.smith@iit.ac.in"
  }'
```

### 3. Get Items from IIT

```bash
curl -X GET http://localhost:8080/api/items/college/iit.ac.in
```

### 4. Get Electronics from IIT

```bash
curl -X GET http://localhost:8080/api/items/college/iit.ac.in/category/Electronics
```

## Features

1. **College Email Validation**: Only users with valid college email domains can register
2. **College-Specific Item Filtering**: Items can be filtered by college domain
3. **Category Filtering**: Items can be filtered by category
4. **User Item Management**: Users can view all their listed items
5. **CRUD Operations**: Full Create, Read, Update, Delete functionality for items
6. **Automatic Timestamps**: Items have created and updated timestamps
7. **Clean Architecture**: Separation of concerns with DTOs, Services, and Controllers

## Error Handling

- Invalid college email domains return 400 Bad Request
- Non-existent users/items return 404 Not Found
- Duplicate emails return 400 Bad Request
- All endpoints include proper error handling

