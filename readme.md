# 🎂 CakeBaker – Spring Boot REST API

A backend **RESTful API** built with **Spring Boot** for managing cake orders 🍰 and bakery operations 🏪.  
The project follows **RESTful design principles**, integrates with **MySQL** for persistence, and leverages **Lombok** & **ModelMapper** for clean and efficient code.

---

## 🚀 Features

- **CRUD Operations** for Cakes & Orders
- RESTful endpoints with proper HTTP methods & status codes
- **MySQL** database integration for persistent storage
- **Lombok** for reducing boilerplate code
- **ModelMapper** for seamless DTO ↔ Entity conversion
- Data validation & structured response handling

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot (REST, Spring Data JPA)
- **Database:** MySQL
- **Utilities:** Lombok, ModelMapper
- **Build Tool:** Maven

---

## 📂 Project Structure

src/main/java/com/Bakery/cakebaker
├── advices # Global Exception Handler and Global Response Handler
├── config # Configurations (ModelMapper, etc.)  
├── controllers # REST Controllers  
├── dtos # Data Transfer Objects  
├── entities # JPA Entities  
├── exceptions # Custom Exception Classes (ResourceNotFoundException, etc.)  
├── repositories # Spring Data JPA Repositories  
└── services # Service classes and interfaces

---

## 🗄️ Database Schema

**Cake**

- `id` (PK)
- `name`
- `flavor`
- `price`
- `available`

**Order**

- `id` (PK)
- `customerName`
- `orderDate`
- `status` (`PENDING` / `COMPLETED`)
- `cake_id` (FK → Cake)

---

## ⚙️ Setup & Installation

1. **Clone Repository**

   ```bash
   git clone https://github.com/agitated-4nn1liator/CakeBaker.git
   cd cakebaker
   ```

2. **Configure Database**

   Create database:

   ```sql
   CREATE DATABASE cakebaker_db;
   ```

   Update `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/cakebaker_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Run Application**
   ```bash
   mvn spring-boot:run
   ```
   App will start on `http://localhost:8080`

---

## 📡 API Endpoints

### Cakes

| Method | Endpoint                    | Description    |
| ------ | --------------------------- | -------------- |
| POST   | /CakeBakerApi/v1/cakes      | Create a cake  |
| GET    | /CakeBakerApi/v1/cakes      | Get all cakes  |
| GET    | /CakeBakerApi/v1/cakes/{id} | Get cake by ID |
| PUT    | /CakeBakerApi/v1/cakes/{id} | Update a cake  |
| DELETE | /CakeBakerApi/v1/cakes/{id} | Delete a cake  |

### Orders

| Method | Endpoint                     | Description     |
| ------ | ---------------------------- | --------------- |
| POST   | /CakeBakerApi/v1/orders      | Place an order  |
| GET    | /CakeBakerApi/v1/orders      | Get all orders  |
| GET    | /CakeBakerApi/v1/orders/{id} | Get order by ID |
| PUT    | /CakeBakerApi/v1/orders/{id} | Update an order |
| DELETE | /CakeBakerApi/v1/orders/{id} | Cancel an order |

---

## Example Request (Create Cake)

POST `/CakeBakerApi/v1/cakes`  
Content-Type: application/json

```json
{
  "name": "Black Forest",
  "flavor": "Cohocolate",
  "price": 450.0,
  "available": true
}
```

**Response (201 Created):**

```json
{
  "timestamp": "01:43:28 17-43-2025",
  "data": {
    "id": 1,
    "name": "Black Forest",
    "flavor": "Cohocolate",
    "price": 450.0,
    "available": true
  },
  "errors": null
}
```

---

## 🧪 Testing

- Use **Postman** or **cURL** to test endpoints
- Validate response codes:
  - `201 Created` – Successful resource creation
  - `200 OK` – Successful retrieval/update
  - `204 No Content` – Successful deletion
  - `404 Not Found` – Invalid resource ID

---

## 📦 Build & Run JAR

```bash
mvn clean package
java -jar target/cakebaker-0.0.1-SNAPSHOT.jar
```

---

## 🤝 Contributing

Pull requests are welcome! Fork and submit improvements.

---

### Author

**agitated annihilator**
🔗 [agitated-4nn1liator](https://github.com/agitated-4nn1liator)

---
