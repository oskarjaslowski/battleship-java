# 🚢 Battleship Game (Java Swing)

## 📌 Author

**Oskar Jasłowski**

The code uses Polish class names (e.g. `Plansza`, `Przeciwnik`, `Statek`)
as the project was developed as part of a university course.

---

## 🎯 Project Overview

This project is a **Battleship game** implemented in **Java** using the **Swing** library for the graphical user interface.

The game allows the player to compete against an AI opponent and includes a system for saving and loading player scores.

Project structure:

* 23 public classes (including 2 abstract classes)
* 1 record (`Gracz`) used for storing player nickname and score

---

## 🕹️ Features

### 🎮 Gameplay

* Player vs Computer
* 3 difficulty levels:

  * Easy
  * Medium
  * Hard

### 💾 Score System

* Save results to a JSON file
* Load and display leaderboard
* Store nickname and score

### 🧠 AI Opponents

* **Easy AI** – random shots
* **Medium AI** – basic hit analysis
* **Hard AI** – advanced logic and prediction

---

## 🖥️ User Interface

After launching the game, a menu appears with options:

* ▶️ Start Game
* 📊 Scores
* 📖 Instructions
* ❌ Exit

### ⚙️ Gameplay Flow

1. Select difficulty level
2. Place ships:

   * Left mouse button – drag & drop
   * Right mouse button – rotate ship
3. Click **"Accept"** to start the game
4. Destroy all enemy ships before they destroy yours

---

## 🚢 Ship Types

### 🔴 Standard Ships

* Differ only in length
* Marked in red

### ⭐ Special Ships

* **Navigator**
  → Reveals location of another ship after being destroyed

* **Battleship (Armored)**
  → Requires 2 hits to sink

* **Bomber**
  → Causes an explosion affecting multiple fields

* **Destroyer**
  → Triggers a double attack when hit

---

## 🧱 Project Structure

### 🪟 GUI (Swing)

* `Main` – main window (JFrame)
* `Menu` – menu panel (JPanel)
* `Plansza` (abstract)

  * `PlanszaGracza`
  * `PlanszaPrzeciwnika`

### 🔘 Components

* `Pole` – board field (JButton)
* `Przycisk` – button logic

### 💬 Dialogs (JDialog)

* `Instrukcja`
* `Wyniki`
* `EkranKońcowy`

### 🤖 AI

* `Przeciwnik`
* `PrzeciwnikŁatwy`
* `PrzeciwnikTrudny`

### 🚢 Ships

* `Statek` (abstract)
* Various ship types (Navigator, Bomber, etc.)

### 👤 Player Data

* `Gracz` (record)

  * JSON save/load

---

## ▶️ How to Run

```bash
git clone https://github.com/your-username/battleship-java.git
```

Open the project in your IDE (e.g. IntelliJ / Eclipse) and run:

```bash
Main.java
```

---

## 📦 Technologies

* Java
* Swing
* JSON

---


## 📄 License

This project is licensed under the **MIT License**.

---

## 💡 Future Improvements

* Multiplayer mode
* Better graphics (JavaFX)
* Animations
* Online leaderboard
