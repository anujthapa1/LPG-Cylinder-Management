cat << 'EOF' > README.md
# ⛽ NOC LPG Cylinder Management System

A Java Swing desktop application built to manage domestic and commercial LPG cylinder distributions, validate buyer limits, and calculate pricing with government subsidies and bulk commercial discounts.

---

## 🌟 Key Features

* **OOP Architecture:** Utilizes Abstraction, Polymorphism, Inheritance, and Encapsulation across model classes.
* **Domestic Subsidies & Quota Tracking:** Deducts subsidies from base prices and enforces a maximum quota of 2 domestic cylinders per citizenship ID per month.
* **Commercial Bulk Discounts:** Automatically calculates tier-based discounts (3% for ≥ 5 units, 5% for ≥ 10 units) based on order volume.
* **Data Persistence:** Built-in Export and Load functionality to read/write formatted record files (`.txt`).
* **Input Validation & Safety:** Checks for duplicate Cylinder IDs, duplicate Booking IDs, and precise 12-digit Citizenship numbers.

---

## 🛠️ Tech Stack & Concepts

* **Language:** Java (JDK 21)
* **GUI Framework:** Java Swing (`JFrame`, `JPanel`, `JTable`, `JFileChooser`)
* **Core Concepts:** Abstract Classes, Method Overriding, Dynamic Method Dispatch, File I/O, Regular Expressions (Regex)

---

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone git@github.com:anujthapa1/LPG-Cylinder-Management.git
   cd LPG-Cylinder-Management
