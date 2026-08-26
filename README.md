# ⛽ NOC LPG Cylinder Management System

> A Java Swing desktop application for managing **domestic and commercial LPG cylinder bookings, pricing, quotas, discounts, validation, and record persistence**.

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" alt="Java 21">
  <img src="https://img.shields.io/badge/GUI-Java%20Swing-blue?style=for-the-badge" alt="Java Swing">
  <img src="https://img.shields.io/badge/Architecture-OOP-success?style=for-the-badge" alt="OOP">
  <img src="https://img.shields.io/github/license/anujthapa1/LPG-Cylinder-Management?style=for-the-badge" alt="License">
</p>

<p align="center">
  <a href="#-overview">Overview</a> •
  <a href="#-features">Features</a> •
  <a href="#-architecture">Architecture</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-getting-started">Getting Started</a> •
  <a href="#-future-improvements">Future Improvements</a>
</p>

---

## 📌 Overview

The **NOC LPG Cylinder Management System** is a desktop-based management application developed with **Java Swing**.

The system is designed to handle LPG cylinder bookings for both **domestic and commercial customers**, while applying business rules such as domestic purchase quotas, government subsidies, commercial bulk discounts, customer validation, and booking record management.

The project demonstrates the practical implementation of **Object-Oriented Programming (OOP)** principles together with GUI development, file handling, input validation, and business logic.

---

## ✨ Features

### 🏠 Domestic Cylinder Management

* Government subsidy calculation for domestic cylinders.
* Monthly purchase quota enforcement.
* Maximum **2 domestic cylinders per Citizenship ID per month**.
* Automatic calculation of the final payable price.
* Citizenship number validation.

### 🏢 Commercial Cylinder Management

* Supports commercial LPG cylinder bookings.
* Automatic bulk-discount calculation.
* **3% discount** for orders of 5 or more cylinders.
* **5% discount** for orders of 10 or more cylinders.

### 📋 Booking Management

* Unique Booking ID validation.
* Unique Cylinder ID validation.
* Domestic and commercial booking handling.
* Customer information validation.
* Structured booking records.

### 💾 File Persistence

* Export booking records to `.txt` files.
* Load previously saved records.
* Formatted text-based data storage.
* Java File I/O implementation.

### 🔐 Input Validation

The application validates:

* 12-digit Citizenship Numbers.
* Duplicate Booking IDs.
* Duplicate Cylinder IDs.
* Booking-related input fields.
* Domestic purchase quota restrictions.

---

## 🧠 OOP Implementation

One of the primary goals of this project is to demonstrate core **Object-Oriented Programming principles**.

| OOP Concept                 | Application                                                         |
| --------------------------- | ------------------------------------------------------------------- |
| **Abstraction**             | Common cylinder behavior is defined through abstract models         |
| **Inheritance**             | Domestic and commercial cylinder types inherit common functionality |
| **Polymorphism**            | Different cylinder types provide specialized implementations        |
| **Encapsulation**           | Model data and behavior are organized within dedicated classes      |
| **Method Overriding**       | Specialized cylinder classes override common methods                |
| **Dynamic Method Dispatch** | Runtime polymorphism determines the appropriate implementation      |

---

## 🏗️ Architecture

The application follows an object-oriented structure where the **Swing interface**, business logic, model classes, and file persistence work together.

```text
┌──────────────────────────────────────┐
│              GUI Layer               │
│   JFrame • JPanel • JTable • Forms   │
└───────────────────┬──────────────────┘
                    │
                    ▼
┌──────────────────────────────────────┐
│           Business Logic             │
│ Pricing • Quota • Discounts •        │
│ Validation • Booking Management      │
└───────────────────┬──────────────────┘
                    │
                    ▼
┌──────────────────────────────────────┐
│             Model Layer              │
│ Cylinder • Domestic • Commercial     │
└───────────────────┬──────────────────┘
                    │
                    ▼
┌──────────────────────────────────────┐
│          Persistence Layer           │
│       Export / Load • TXT Files      │
└──────────────────────────────────────┘
```

---

## 💰 Business Rules

### Domestic Pricing

The domestic cylinder price is calculated after applying the applicable subsidy.

```text
Final Price = Base Price − Subsidy
```

The system also checks the customer's monthly domestic cylinder quota.

```text
Maximum Domestic Cylinders
= 2 per Citizenship ID per month
```

### Commercial Discounts

Commercial orders receive volume-based discounts:

```text
┌─────────────────────┬───────────────┐
│ Order Quantity      │ Discount      │
├─────────────────────┼───────────────┤
│ Less than 5         │ 0%            │
│ 5 – 9               │ 3%            │
│ 10 or more          │ 5%            │
└─────────────────────┴───────────────┘
```

---

## 🛠️ Tech Stack

### Programming

* **Java 21**

### GUI

* **Java Swing**

  * `JFrame`
  * `JPanel`
  * `JTable`
  * `JFileChooser`

### Concepts & Technologies

* Object-Oriented Programming
* Abstract Classes
* Inheritance
* Polymorphism
* Encapsulation
* Method Overriding
* Dynamic Method Dispatch
* File I/O
* Regular Expressions
* Input Validation

---

## 📂 Project Structure

```text
LPG-Cylinder-Management/
├──src/
│   └──  CommercialCylinder.java
│   └── DomesticCylinder.java
│   └── LPGCylinder.java
│   └── NOCApp.java
│
├── .gitignore
├── LICENSE
└── README.md
```

The `src` directory contains the application's Java source code, while `documentation` contains supporting project documentation.

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

* **JDK 21 or later**
* Git
* A Java-compatible IDE

Recommended IDEs include:

* IntelliJ IDEA
* Eclipse
* Visual Studio Code

### 1. Clone the Repository

```bash
git clone https://github.com/anujthapa1/LPG-Cylinder-Management.git
```

### 2. Navigate to the Project

```bash
cd LPG-Cylinder-Management
```

### 3. Open the Project

Open the project in your preferred Java IDE and configure it to use **JDK 21**.

### 4. Run the Application

Locate the application's main Java class inside the `src` directory and run it using your IDE.

---

## 🧪 Validation & Error Handling

The application implements validation to maintain consistent and reliable booking records.

Examples include:

* Invalid Citizenship Number detection.
* Duplicate Booking ID detection.
* Duplicate Cylinder ID detection.
* Domestic quota enforcement.
* Invalid booking information handling.
* File loading and export handling.

---

## 📈 Future Improvements

Potential improvements for future versions include:

* [ ] MySQL database integration
* [ ] User authentication
* [ ] Role-based access control
* [ ] PDF invoice generation
* [ ] Advanced booking search and filtering
* [ ] Customer management dashboard
* [ ] Booking history and reporting
* [ ] Improved UI/UX
* [ ] Automated testing
* [ ] CI/CD with GitHub Actions

---

## 🎓 Learning Outcomes

This project provides practical experience in:

* Designing object-oriented applications.
* Developing desktop GUIs using Java Swing.
* Applying inheritance and polymorphism.
* Implementing business rules in software.
* Working with files and persistent records.
* Validating structured user input.
* Organizing a Java application into maintainable components.

---

## 👨‍💻 Author

### Anuj Thapa

**BSc (Hons) Computing Student**

Pokhara, Nepal

<p>
  <a href="https://github.com/anujthapa1">
    <img src="https://img.shields.io/badge/GitHub-anujthapa1-black?style=flat-square&logo=github" alt="GitHub">
  </a>
  <a href="https://thapaanuj.com.np">
    <img src="https://img.shields.io/badge/Portfolio-thapaanuj.com.np-blue?style=flat-square&logo=google-chrome" alt="Portfolio">
  </a>
</p>

---

## 📄 License

This project is licensed under the **MIT License**.

See the [`LICENSE`](LICENSE) file for more information.

---

<p align="center">
  <b>Built with Java ☕ • Swing 🖥️ • Object-Oriented Programming 🧠</b>
</p>

<p align="center">
  ⭐ If you find this project useful, consider giving it a star!
</p>
