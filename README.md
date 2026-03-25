# 🚢 Gra w Statki (Java Swing)

## 📌 Autor

**Oskar Jasłowski**

---

## 🎯 Opis projektu

Celem projektu było stworzenie gry **Statki** w języku **Java** z wykorzystaniem biblioteki **Swing**.
Gra umożliwia rozgrywkę przeciwko komputerowi oraz zapis i odczyt wyników graczy.

Projekt zawiera:

* 23 klasy publiczne (w tym 2 abstrakcyjne)
* 1 rekord (`Gracz`) do przechowywania wyników

---

## 🕹️ Funkcjonalności

### 🎮 Tryb gry

* Gra przeciwko komputerowi (AI)
* 3 poziomy trudności:

  * łatwy
  * średni
  * trudny

### 💾 System wyników

* zapis wyników do pliku JSON
* możliwość odczytu tabeli wyników
* zapis pseudonimu gracza i liczby punktów

### 🧠 Sztuczna inteligencja

* **Łatwy przeciwnik** – losowe strzały
* **Średni przeciwnik** – podstawowa analiza trafień
* **Trudny przeciwnik** – zaawansowana logika i przewidywanie

---

## 🖥️ Interfejs użytkownika

Po uruchomieniu gry dostępne jest menu:

* ▶️ Rozpocznij grę
* 📊 Wyniki
* 📖 Instrukcja
* ❌ Wyjście

### ⚙️ Rozgrywka

1. Wybór poziomu trudności
2. Rozstawianie statków:

   * lewy przycisk myszy – przeciąganie statku
   * prawy przycisk myszy – zmiana orientacji
3. Kliknięcie **„Akceptuj”** rozpoczyna grę
4. Celem jest zatopienie wszystkich statków przeciwnika

---

## 🚢 Rodzaje statków

### 🔴 Standardowe

* różnią się długością
* oznaczone kolorem czerwonym

### ⭐ Specjalne

* **Nawigator**
  → po zatopieniu ujawnia lokalizację innego statku

* **Pancernik**
  → wymaga 2 trafień do zatopienia

* **Bombowiec**
  → wywołuje eksplozję obejmującą obszar planszy

* **Niszczyciel**
  → po trafieniu aktywuje podwójny atak

---

## 🧱 Struktura projektu

### 🪟 Interfejs (Swing)

* `Main` – główne okno (JFrame)
* `Menu` – panel menu (JPanel)
* `Plansza` (abstrakcyjna)

  * `PlanszaGracza`
  * `PlanszaPrzeciwnika`

### 🔘 Komponenty GUI

* `Pole` – element graficzny pola (JButton)
* `Przycisk` – obsługa przycisków

### 💬 Okna dialogowe (JDialog)

* `Instrukcja`
* `Wyniki`
* `EkranKońcowy`

### 🤖 AI

* `Przeciwnik`
* `PrzeciwnikŁatwy`
* `PrzeciwnikTrudny`

### 🚢 Statki

* `Statek` (abstrakcyjna)
* różne typy statków (np. Nawigator, Pancernik itd.)

### 👤 Dane gracza

* `Gracz` (record)

  * zapis do JSON
  * odczyt wyników

---

## ▶️ Uruchomienie

1. Sklonuj repozytorium:

```bash
git clone https://github.com/twoj-login/statki-java.git
```

2. Otwórz projekt w IDE (np. IntelliJ / Eclipse)

3. Uruchom klasę:

```bash
Main.java
```

---

## 📦 Technologie

* Java
* Swing (GUI)
* JSON (zapisywanie danych)

---

## 📄 Licencja

Projekt udostępniony na licencji **MIT**.

---

## 📸 Możliwe rozszerzenia

* tryb multiplayer
* lepsza grafika (JavaFX)
* animacje
* ranking online

---

## 💡 Uwagi

Projekt został wykonany w celach edukacyjnych w ramach zajęć akademickich.
