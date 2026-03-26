# 📝 Feedback Portal

A full-stack web application where **students can submit anonymous feedback** to the **Head of Department (HOD)** without revealing their identity. The HOD can securely **view, manage, and act on feedback** through an admin dashboard.

---

## 🎯 Use Cases

### 👩‍🎓 For Students:

* ✅ Login and submit genuine feedback without fear of being identified
* ✅ Share opinions about classes, teaching, labs, or facilities
* ✅ No personal information (such as name or ID) is stored with the feedback

### 👨‍🏫 For HODs (Admins):

* ✅ Login with secure credentials
* ✅ View all feedback submitted by students
* ✅ Use feedback insights to improve academic or administrative processes
* ✅ Manage and delete feedback when necessary

---

## 📌 Features

* 🔒 Secure Login & Registration
* 🧾 Anonymous Feedback Submission
* 🧑‍💼 Admin (HOD) Dashboard to View Feedback
* 🔍 Search and Filter Feedback
* 🗑️ Delete Feedback Option
* 🌐 Spring Boot REST API
* 💾 MySQL Database Integration

---

## 🔄 Application Flow

Student → Submit Feedback → Stored in Database → Admin Dashboard → View / Manage Feedback

---

## ⚙️ Setup Instructions

1. Clone the repository

   ```
   git clone https://github.com/mounikamalineni26/feedback-portal.git
   ```

2. Open the project in IntelliJ IDEA

3. Configure MySQL Database

   * Create a database named: `anonymous_feedback_db`
   * Update `application.properties` with your MySQL username & password

4. Run the Spring Boot application

5. Open your browser and visit:

   ```
   http://localhost:8080
   ```

---
## 🖼️ Screenshots

### Home Page
![Home](images/01-home-page.png)

### Login Page
![Login](images/02-login-page.png)

### Register Page
![Register](images/03-register-page.png)

### Submit Feedback
![Feedback](images/04-submit-feedback.png)

### Admin Dashboard
![Dashboard](images/05-admin-dashboard.png)



---

## 🛠️ Tech Stack

* **Frontend**: HTML, CSS, JavaScript
* **Backend**: Java, Spring Boot, Spring Security
* **Database**: MySQL
* **Tools**: IntelliJ IDEA, Postman, MySQL Workbench

---

## 🔐 Default Admin (HOD) Login

* **Username**: `admin`
* **Password**: `admin123`

⚠️ These credentials are for testing purposes only. Use secure authentication in production.

---

## ✅ Testing

* All backend APIs tested using **Postman**
* End-to-end workflow verified for student submission and admin view

---

## 🤝 Author

**Mounika Malineni**

📧 [mounikamalineni63@gmail.com](mailto:mounikamalineni63@gmail.com)
🔗 [GitHub Profile](https://github.com/mounikamalineni26)
🔗 [LinkedIn Profile](https://www.linkedin.com/in/mounikamalineni)

---
