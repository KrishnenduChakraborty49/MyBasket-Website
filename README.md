### MyBasket 🛒
MyBasket is a backend-driven e-commerce application built using Java and Spring Boot.
It provides secure authentication, role-based authorization, and RESTful APIs to manage users, products, carts, and orders.

---

### 📌 Project Overview
MyBasket simulates a real-world online shopping system where users can:
(i) Register and log in securely
(ii) Browse products
(iii) Add items to a cart
(iv) Place orders
The backend follows layered architecture, DTO pattern, and industry best practices.

---

### 🚀 Features
- User registration and login
- JWT-based authentication using Spring Security
- Role-based authorization (USER / ADMIN)
- Product and category management
- Add to cart and checkout functionality
- RESTful API design using DTO pattern
- Centralized exception handling and validation

---

### 🛠 Tech Stack
- **Backend:** Java, Spring Boot  
- **Security:** Spring Security (JWT Authentication)  
- **ORM:** JPA / Hibernate  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **IDE:** IntelliJ IDEA  

---

### 🧱 Project Architecture
Controller → Service → Repository → Database
            ↓
           DTO

**Controller Layer:**- Handles HTTP requests

**Service Layer:** – Business logic

**Repository LayerL:** – Database access

**Security Layer:** – JWT authentication & authorization

---

### 🔐 Authentication & Authorization

-JWT token is generated after login

-Token must be passed in the request header for secured APIs

-Role-based access:

-USER → cart, order, view products

-ADMIN → manage products & categories

---
### Authorization Header Format

Authorization: Bearer <JWT_TOKEN>

---

### 🔗 REST API Endpoints

## 🔑 Authentication APIs

 # Method         Endpoint	           Description

 POST            /api/auth/register	           Register a new user
 
 POST	    /api/auth/login	           Login and generate JWT token

## 📦 Product APIs

 # Method           Endpoint              Description 
 
  GET	        /api/products	        Get all products
  
  GET	        /api/products/{id}      Get product by ID
  
  POST	        /api/products	        Add product (ADMIN only)
  
  PUT	        /api/products/{id}      Update product (ADMIN only)
  
  DELETE	        /api/products/{id}      Delete product (ADMIN only

 ## 🛒 Cart APIs
 
# Method	      Endpoint	                      Description

  POST	        /api/cart/add	                  Add product to cart
  
  GET	          /api/cart	                      View cart
  
  DELETE	      /api/cart/remove/{productId}	  Remove product from cart

---

### 🧪 API Testing with Postman

All REST APIs were tested using **Postman**.

## Authentication Flow

1.Register → /api/auth/register

2.Login → /api/auth/login

3.Copy JWT token

4.Add token to Authorization header

5.Access secured APIs

---

### 📌 Future Enhancements

1.Payment gateway integration

2.Swagger API documentation

3.Frontend integration

### 👨‍💻 Author

Krishnendu Chakraborty

Aspiring Java Backend Developer (Java | Spring Boot | REST APIs | JWT | MySQL)
