# Blog App – Fullstack Blogging Application

**Blog App** is a modern fullstack blogging platform that allows users to create, manage, and share articles seamlessly — built with a scalable backend and a responsive frontend.

> Built using **Java Spring Boot** (Backend) and React + Vite (Frontend), with containerized setup using Docker.
---

## ✨ What is Blog App?

Blog App is a simple yet powerful blogging platform designed to demonstrate how a real-world content system works — from authentication to content management.

**Goals:**
- ✍️ Enable users to create and publish blog posts
- 🔐 Provide secure authentication and authorization
- 📚 Manage articles with structured data and clean APIs
- ⚡ Demonstrate production-ready fullstack practices

---

## ⚙️ Tech Stack

| Backend (Java)        | Frontend (Vue.js) |
|-----------------------|-------------------|
| Java 21               | React             |
| Spring Boot 3         | Vite              |
| Spring Security + JWT | Tailwind CSS      |
| Spring Validation     | Axios             |
| PostgreSQL            | Node.js & npm     |
| Apache Maven          |                   |

---

## ✅ Main Features

- 🔐 JWT authentication and secure login
- ✍️ Post Management (CRUD)
- 📄 Public Blog Feed
- ⚠️ Consistent API Response
- ⚠️ Clean error handling with JSON responses

---

## 🚀 Installation Guide

1. Clone the repository
   ```bash
   git clone https://github.com/budioct/fullstack-blog-app.git
   ```

2. Start with Docker
   `Make sure Docker is installed and running on your machine.`
    ```bash
    docker-compose up -d
    ```

3. Update DB config (backend)
   Edit `src/main/resources/application.properties` and update:
   ```properties
   spring.datasource.username=POSTGRES_USER
   spring.datasource.password=POSTGRES_PASSWORD
   ```

4. Build and run the backend
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   Runs on: `http://localhost:8080`


5. Install frontend dependencies
   ```bash
   cd frontend/
   npm install
   ```

6. Run frontend app
   ```bash
   npm run dev
   ```
   Runs on: `http://localhost:5173`

---

## 🧪 Demo Accounts

**Login with demo user:**
```txt
Email   : budhi@test.com
Password: 12345678
```

---

## 📮 API Docs

- Postman link: *coming soon*
- Postman collection is available inside the `docs/` folder

---

## 🙌 About the Creator

Hi, I'm **<a href="https://www.linkedin.com/in/budhi-octaviansyah/"> Budhi Octaviansyah </a>** 👋

I built this project as part of my journey to deepen my understanding of fullstack development — especially in designing scalable backend systems using Spring Boot and integrating them with modern frontend tools like React.

> Thanks for checking out Blog App! I hope it inspires or helps you build better systems too. Feel free to fork or give feedback!

---

🚀 Happy coding and stay energized! – <a href="https://www.linkedin.com/in/budhi-octaviansyah/"> Budhi Octaviansyah </a>
