<p align="center">
  <img src="src/view/images/logo.png" alt="Sunrise Dental Clinic Logo" width="200"/>
</p>

<h1 align="center">Sunrise Dental Clinic Management System</h1>

An enterprise-grade, distributed Java desktop application built to manage the daily operations of the Sunrise Dental Clinic. This system features a robust 3-tier MVC architecture, advanced MySQL database integration, real-time SMTP email notifications, and automated PDF invoice generation.

---

## 🚀 Key Features

* **Secure User Authentication & Session Management:** Custom `UserSession` singleton manages active sessions with cryptographically secure UUID tokens to track staff accountability.
* **Advanced Appointment Scheduling:** Automated alphanumeric ID generation and strict Regex input validation.
* **Automated Billing & PDF Invoices:** Uses MySQL Stored Procedures for atomic arithmetic calculations and **iTextPDF** to generate highly formatted, printable receipts.
* **Dual-Channel Notifications:** Implements the Factory Method pattern to dispatch real-time SMTP Emails (with inline logos and PDF attachments via JavaMail) and console-simulated SMS telemetry.
* **Native Database Business Rules:** Employs MySQL `BEFORE INSERT` triggers to natively block double-booking race conditions.
* **Multithreaded UI:** Background worker threads power a live system clock and dashboard analytics without freezing the Java Swing Event Dispatch Thread (EDT).

---

## 🏗️ System Architecture

The application is structured using a strict **3-Tier MVC (Model-View-Controller)** design to ensure high cohesion and loose coupling:

1. **Presentation Tier (`view`):** Customized Java Swing GUI forms (`JFrame`) with real-time `JOptionPane` validation.
2. **Business Logic & Service Tier (`service`, `pattern`):** Services orchestrate domain rules, while GoF Design Patterns (Singleton, Factory Method) handle instantiation and state.
3. **Data Access Tier (`dao`, `db`):** Data Access Objects isolate low-level JDBC operations using `PreparedStatement` and `CallableStatement` APIs.

---

## 🛠️ Technology Stack

* **Language:** Java (JDK 11 / 17)
* **GUI Framework:** Java Swing (AWT/Swing)
* **Database:** MySQL (InnoDB Engine)
* **Unit Testing:** JUnit 4
* **Build Tool:** Apache Ant
* **CI/CD:** GitHub Actions
* **External Libraries:**
  * `mysql-connector-j` (JDBC Driver)
  * `itextpdf` (PDF Generation)
  * `javax.mail` (SMTP Email Dispatch)
  * `jcalendar` (JDateChooser UI Component)

---

## ⚙️ Installation & Setup

### 1. Database Configuration
1. Install and start **WAMP / XAMPP / MySQL Workbench**.
2. Create a new MySQL database named `sunrise_dental_db`.
3. Execute the provided SQL script located in the repository root to build the schema, triggers, and stored procedures.
4. The script automatically creates a default admin user:
   * **Username:** `admin`
   * **Password:** `admin123`

### 2. Project Setup
0. Clone this repository:
   ```bash
 https://github.com/ilpeiris/Sunrise-Dental-Clinic.git


1. Open the project in **Apache NetBeans IDE**.

2. Ensure all provided `.jar` files in the `/lib` directory are added to your project's **Libraries** path.

3. Clean and Build the project.

## 3. SMTP Email Configuration (Optional)

If you wish to test the live email functionality:

1. Open `src/pattern/EmailNotification.java`.
2. Replace the placeholder credentials with a valid Gmail address and **Google App Password**.

## 🧪 Testing (TDD)

This project was built utilizing **Test-Driven Development (TDD)**. The automated test suite leverages **JUnit 4** to validate domain logic, regex boundaries, and factory instantiations without relying on the GUI.

To run the tests:

* In NetBeans, right-click the project and select **Test**.
* Alternatively, run the custom console-based test runner located at `src/test_case/SunriseDentalTestRunner.java`.

---

## Author

**M.D.Isuru Lakmal Peiris**
GitHub: [@ilpeiris](https://github.com/ilpeiris)
LinkedIn: [linkedin.com/in/ilpeiris](https://linkedin.com/in/ilpeiris)

## 📜 License & Academic Integrity

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details. Developed as part of the **CIS6003: Advanced Programming** module. This codebase demonstrates applied software carpentry, **Object-Oriented Programming (OOP)** pillars, and defensive coding techniques.




