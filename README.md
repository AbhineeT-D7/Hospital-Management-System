# 🏥 Hospital Management System

A RESTful backend API for managing hospital operations including patients, staff, problems, and receipts. Built with **Spring Boot** and secured with **JWT Authentication**.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core programming language |
| Spring Boot | Backend framework |
| Spring Security + JWT | Authentication & Authorization |
| Maven | Build & dependency management |
| PostgreSQL | Relational database |
| JPA / Hibernate | ORM for database interaction |

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/abhi/gov_hos_app/
│   │   ├── controller/        # REST Controllers (Patient, Staff, Problem, Receipt)
│   │   ├── dto/               # Data Transfer Objects
│   │   ├── entity/            # JPA Entities (Patient, Staff, Problem, Receipt, etc.)
│   │   │   └── enums/         # Enums (City, Department, Role, ProblemStatus)
│   │   ├── exception/         # Custom Exception Handling
│   │   ├── repository/        # Spring Data JPA Repositories
│   │   ├── security/          # JWT Filter, Token Provider, Security Config
│   │   ├── service/           # Business Logic Layer
│   │   └── util/              # API Path Constants
│   └── resources/
│       └── application.properties
└── test/                      # Unit Tests
```

---

## 🚀 Features

- ✅ **Patient Management** — Add, update, view, and manage patients
- ✅ **Staff Management** — Manage hospital staff with roles and departments
- ✅ **Problem Tracking** — Track patient problems with status updates
- ✅ **Receipt Management** — Generate and manage billing receipts
- ✅ **JWT Security** — Secure endpoints with token-based authentication
- ✅ **Role-Based Access** — Different access levels based on user roles
- ✅ **Exception Handling** — Global exception handler with custom responses

---

## ⚙️ Setup & Installation

### Prerequisites
- Java 17+
- Maven 3.6+
- PostgreSQL 13+

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/AbhineeT-D7/Hospital-Management-System.git
   cd Hospital-Management-System
   ```

2. **Configure the database**

   Open `src/main/resources/application.properties` and update:
   ```properties
   server.port=8080
   spring.datasource.url=jdbc:postgresql://localhost:5432/Hospital
   spring.datasource.username=your_postgres_username
   spring.datasource.password=your_postgres_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   ```

3. **Set up the database**

   Create a database named `Hospital` in PostgreSQL:
   ```sql
   CREATE DATABASE Hospital;
   ```
   Then run the SQL script:
   ```bash
   psql -U postgres -d Hospital -f sql.txt
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The server will start at: `http://localhost:8080`

---

## 🔐 Authentication

This API uses **JWT (JSON Web Token)** for authentication.

1. Login with your credentials to receive a token
2. Include the token in the `Authorization` header for protected routes:
   ```
   Authorization: Bearer <your_token>
   ```

---

## 📌 API Endpoints

### 👤 Patient
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/patients` | Get all patients |
| GET | `/api/patients/{id}` | Get patient by ID |
| POST | `/api/patients` | Add a new patient |
| PUT | `/api/patients/{id}` | Update patient |
| DELETE | `/api/patients/{id}` | Delete patient |

### 👨‍⚕️ Staff
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/staff` | Get all staff |
| POST | `/api/staff` | Add new staff |
| PUT | `/api/staff/{id}` | Update staff |
| DELETE | `/api/staff/{id}` | Delete staff |

### 🩺 Problem
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/problems` | Get all problems |
| POST | `/api/problems` | Add a problem |
| PUT | `/api/problems/{id}` | Update problem status |

### 🧾 Receipt
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/receipts` | Get all receipts |
| POST | `/api/receipts` | Generate receipt |

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 👤 Author

**Abhineet**
- GitHub: [@AbhineeT-D7](https://github.com/AbhineeT-D7)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
