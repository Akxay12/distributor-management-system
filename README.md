# 🚀 Distributor Management System

A backend system built using Spring Boot for managing distributors, products, and orders with analytics features.

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

---

## 📌 Features

### 👤 Dealer Management

* Create Dealer
* Get All Dealers
* Get Dealer by ID / Name / City
* Update Dealer
* Delete Dealer

---

### 📦 Product Management

* Create Product
* Get All Products
* Get Product by ID / Name
* Update Product
* Delete Product

---

### 🧾 Order Management

* Create Order (auto price calculation)
* Get All Orders
* Get Orders by Dealer
* Get Orders by Product
* Delete Order

---

### 📊 Analytics

* Total Revenue
* Product-wise Sales
* Recent Orders

---

### ⚠️ Exception Handling

* Global Exception Handling using `@RestControllerAdvice`
* Custom Exceptions:

  * BadRequestException
  * ResourceNotFoundException

---

## 🧠 Key Highlights

* Clean layered architecture (Controller → Service → Repository)
* Proper use of JPA relationships
* Business logic implementation (price calculation, validations)
* RESTful API design
* Centralized exception handling

---

## 🚀 How to Run

1. Clone the repository

```bash
git clone https://github.com/your-username/distributor-management-system.git
```

2. Open in IDE (IntelliJ / VS Code)

3. Configure MySQL in `application.properties`

4. Run the application

---

## 📌 API Testing

Use Postman to test APIs.

---

## 👨‍💻 Author

Akshay
