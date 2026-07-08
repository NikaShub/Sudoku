# 🚀 Advanced Java Suite: Intelligent Sudoku Solver & Metropolis DB Manager

A high-performance, independent Java application featuring two standalone modules: an **Intelligent Sudoku Solver (AI Engine)** and a **Metropolis Database Manager (JDBC Client)**. This project focuses on leveraging core Object-Oriented Programming (OOP) principles, efficient data structure management using the Java Collections Framework, and dynamic user interface development with Java Swing.

---

## 🧩 Module 1: Optimized Sudoku Solver

This module features a highly optimized Sudoku puzzle-solving engine that implements a refined version of the **Recursive Backtracking** algorithm.

### ⚙️ Architecture & Engineering Highlights
* **Smart Heuristics (Most Constrained Spots):** Instead of a naive sequential fill, the algorithm analyzes and sorts cells before executing. It prioritizes the "Most Constrained Spots"—cells with the fewest possible valid options—which drastically reduces the overall search space and execution time.
* **Lazy Evaluation (On-the-Fly Calculations):** Storing valid choices beforehand can be inefficient since a single assignment impacts multiple cells. This architecture computes valid cell values dynamically the exact millisecond the algorithm moves to that specific spot.
* **Encapsulation via Inner Classes:** The system relies on a main `Sudoku` class, where individual grid cells are encapsulated as an inner `Spot` class. This hides indexing complexities from the client, allowing clean API calls like `spot.set(6)`.
* **Multi-Solution Support:** Capable of finding and capturing multiple solutions (capped at 100) for a single puzzle matrix, making it excellent for testing and verifying puzzle validity.

### 🖥️ Graphical User Interface (Sudoku GUI)
* **Layout:** Built cleanly with a structured `BorderLayout(4, 4)`.
* **Auto-Check Engine:** Powered by a `DocumentListener` that intercepts real-time text updates on every keystroke. When enabled, the background solver instantly checks and populates solutions as you type.
* **Performance Benchmarking:** Uses `System.currentTimeMillis()` to measure the exact calculation speed down to the millisecond, proving the efficiency of the heuristic solver.

---

## 🗄️ Module 2: Metropolis Database Viewer

A full-stack, lightweight Database Client written completely from scratch to interface with a MySQL database using Swing's `JTable` component.

### ⚙️ Features & Architecture
* **Data Insertion (Add Mode):** Allows users to dynamically input a new city, continent, and population. Upon submission, it writes directly to the DB and immediately updates the view to show the new record.
* **Dynamic Search Engine:** Supports complex filtering on any combination of fields simultaneously. Empty fields are intelligently ignored by the query builder.
* **Dropdown Filters:**
    * *Population Pulldown:* Filter cities that are strictly greater than or less than a target population.
    * *Match Type Pulldown:* Supports both standard **Exact Matches** and flexible **Partial Matches** utilizing SQL `%` wildcards.
* **Custom MVC Architecture:** Implements a custom table model extending `AbstractTableModel`. When fetching new data from the database, it utilizes `fireTableDataChanged()` to seamlessly refresh the UI without tearing down the layout.

---

## 🛠️ Technology Stack
* **Language:** Java 8 / Java 11
* **UI Framework:** Java Swing / AWT
* **Database Connectivity:** MySQL & JDBC
* **Design Patterns:** Encapsulation, Inner Classes, Custom MVC (Table Models), Lazy Evaluation

---

## 🚀 Getting Started

1. Ensure you have a local MySQL server up and running.
2. Execute your database scripts to import the necessary structural data.
3. Update the database connection class with your local Database Name and Username. 
   *(Note: For security reasons, the password fields are left as an empty string `""` before pushing to remote).*
4. Run the `main()` method inside `Sudoku.java` or `MetropolisViewer.java` to start the respective applications.
