# 🧑‍💼 Employee Management System

A full-featured **Java Swing + MySQL** desktop-based HR management system that allows companies to handle all core employee operations, such as managing profiles, tracking attendance (with break time deduction), processing salaries, applying leaves, and securely maintaining records.

---

## 📌 Features

- 👤 **Employee Profile Management**
- ⏱️ **Daily Attendance** (break time excluded)
- 📅 **Leave Application and Tracking**
- 💰 **Salary Calculation** based on working hours
- 📜 **Salary History** management
- 🗑️ **Delete Employee** records
- 🔐 **Login and Logout** system
- 📊 **Admin GUI Interface** built using Java Swing
- 💾 **Data Storage** using MySQL with JDBC connectivity

---

## 🛠️ Tech Stack

| Component    | Technology           |
|--------------|----------------------|
| 💻 Frontend   | Java Swing           |
| 🗄️ Backend     | MySQL               |
| 🔗 Connector  | JDBC                |
| 🧰 IDE        | NetBeans / IntelliJ |
| 🧪 DB Tool    | SQL Command Line     |

---

## 📂 Project Structure

Employee_Management_System/
├── src/
│   └── Employee_Management/
│       ├── LoginPage.java
│       ├── HomePage.java
│       ├── AddProfilePage.java
│       ├── ViewProfilePage.java
│       ├── TakeAttendancePage.java
│       ├── GetAttendancePage.java
│       ├── LeavePage.java
│       ├── GetLeaveListPage.java
│       ├── PaySalaryPage.java
│       ├── SalaryPage.java
│       ├── SalaryHistoryPage.java
│       ├── DeleteEmployeePage.java
│       └── ConnectionClass.java
└── Database/
    └── employee_management.sql  '''




📋 How to Run
Clone the Repository
git clone https://github.com/Dattkh/Employee-Management-System.git
Import in NetBeans / IntelliJ
Open as a Java project.
Ensure JDK 8+ is installed.
Database Setup
Create a MySQL database named employee_management.
Import the provided SQL script in the Database folder.
Run Application
Run LoginPage.java to start the GUI.
Use admin credentials to access full features.

🖥️ User Roles
Role	Access Privileges
Admin	Full access to all modules (create, update, delete)
Employee	View profile, attendance, apply leave

🔐 Security Features
✅ Login Authentication
🔒 Role-Based Access Control
🛡️ PreparedStatements used to prevent SQL injection
🧑‍💻 Passwords stored securely (can be hashed with MD5/SHA)
🧪 Testing Overview

The system was tested using:
Unit Testing (module-wise)
Integration Testing (e.g., attendance linked to salary)
UAT by mock HR and staff users
50+ test cases covering edge conditions
✅ Most test cases passed successfully. (Detailed cases are in /docs)

🚧 Known Limitations
Only single Admin login
Desktop-only application (no web/mobile)
No real-time alerts or biometric integration
Basic GUI design (functional, not fancy)

📘 Project Documentation
For a full system analysis, UML diagrams, flowcharts, design decisions, and screenshots, refer to:
📄 [project report AOOP.pdf](./docs/project_report_AOOP.pdf)

🙌 Author
Made by Datt Kharel
📍 GitHub Profile : Dattkh
🎓 Final Year Diploma in Computer Eng.

