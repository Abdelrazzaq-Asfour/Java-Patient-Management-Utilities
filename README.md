# 🏥 Java Patient Management & Utilities

This Java project includes:

1. ✅ **Patient Priority Queue** (using a circular linked list)
2. 💊 **Medication Dose Calculator** (recursive)
3. 📊 **Array Sorting Toolkit** (QuickSort, SelectionSort, etc.)

---

## 🧠 Features

### 1. Patient Queue System
- Uses a **circular linked list**.
- Patients have:
  - `Name`
  - `Disease type`
  - `Urgency level` (0 = most urgent)
- **Automatically sorted by urgency**.
- Supports:
  - `enqueue()` to add patient
  - `dequeue()` to serve patient
  - `display()` to show current queue

---

### 2. Dose Calculator
- Takes:
  - Initial dose in `mg`
  - Daily decrease as `%` of original dose
- Calculates:
  - Daily doses until reaching zero
  - Total number of days required
- Uses **recursive logic**

---

### 3. Array Sorting Toolkit
- Accepts user-defined integer array.
- Supports formatting into:
  - Sorted
  - Reversed
  - Random
  - Partially Sorted
- Provides:
  - `QuickSort` algorithm
  - `SelectionSort` algorithm

---

## 🚀 How to Use

### 1. Compile
```bash
javac Main.java
