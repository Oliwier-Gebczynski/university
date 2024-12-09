### Klasy systemów wieloprocesorowych

Systemy wieloprocesorowe dzielą się na różne klasy w zależności od sposobu organizacji procesorów i pamięci oraz metody komunikacji między nimi. Najczęściej omawiane klasy to **SMP (Symmetric Multiprocessing)** i **MPP (Massively Parallel Processing)**, które mają różne zastosowania i charakterystyki.

---

### **1. SMP (Symmetric Multiprocessing)**

SMP to architektura, w której wszystkie procesory mają równy dostęp do wspólnej pamięci operacyjnej.

#### **Charakterystyka:**
1. **Wspólna pamięć:**
   - Wszystkie procesory korzystają z tej samej przestrzeni pamięci.
   - Pamięć operacyjna jest współdzielona, a dostęp do niej jest zarządzany przez kontrolery pamięci.

2. **Jednolity dostęp do zasobów:**
   - Każdy procesor ma równorzędny dostęp do urządzeń wejścia-wyjścia (np. dysków, sieci).

3. **Spójność pamięci:**
   - Spójność pamięci podręcznej jest zapewniana za pomocą protokołów, takich jak MESI.

4. **Zastosowania:**
   - Serwery baz danych, aplikacje wymagające intensywnej komunikacji między procesorami.

#### **Zalety SMP:**
- Prosta w implementacji i zarządzaniu.
- Łatwość programowania dzięki wspólnej pamięci.

#### **Wady SMP:**
- Ograniczona skalowalność, ponieważ zbyt duża liczba procesorów powoduje przeciążenie wspólnej magistrali.

---

### **2. MPP (Massively Parallel Processing)**

MPP to architektura, w której każdy węzeł ma własną, lokalną pamięć i procesory, a komunikacja między węzłami odbywa się przez sieć.

#### **Charakterystyka:**
1. **Rozproszona pamięć:**
   - Każdy węzeł ma swoją odrębną pamięć, co eliminuje konieczność współdzielenia pamięci operacyjnej.

2. **Przesył komunikatów:**
   - Węzły komunikują się ze sobą za pomocą wiadomości przesyłanych przez sieć (np. sieci InfiniBand lub Ethernet).

3. **Wysoka skalowalność:**
   - Architektura pozwala na łatwe dodawanie nowych węzłów, co czyni ją idealną do budowy superkomputerów.

4. **Zastosowania:**
   - Superkomputery, symulacje naukowe, analiza dużych zbiorów danych.

#### **Zalety MPP:**
- Wysoka skalowalność i wydajność.
- Brak ograniczeń związanych ze wspólną pamięcią.

#### **Wady MPP:**
- Złożoność programowania, ponieważ wymaga zarządzania komunikacją między węzłami.
- Wyższy koszt infrastruktury sieciowej.

---

### **Porównanie SMP i MPP**

| **Cecha**                  | **SMP**                                 | **MPP**                                   |
|----------------------------|------------------------------------------|-------------------------------------------|
| **Rodzaj pamięci**         | Wspólna                                 | Rozproszona                              |
| **Komunikacja między CPU** | Bezpośredni dostęp                      | Przesył komunikatów                      |
| **Skalowalność**           | Ograniczona                             | Bardzo wysoka                            |
| **Zastosowania**           | Aplikacje wymagające wspólnej pamięci    | Superkomputery i zadania masowo równoległe |
| **Zarządzanie spójnością** | Protokoły, np. MESI                     | Brak spójności, niezależne węzły          |

---

### **Przykłady:**
- **SMP:** Serwer baz danych z 4 procesorami, które współdzielą pamięć operacyjną.
- **MPP:** Superkomputer składający się z tysięcy węzłów, każdy z własnym procesorem i pamięcią, używany do symulacji klimatu lub modelowania molekularnego.

---

### **Podsumowanie**

SMP i MPP to dwa różne podejścia do wieloprocesorowych systemów komputerowych. **SMP** zapewnia prostotę i wspólną pamięć, ale jest ograniczona pod względem skalowalności. Z kolei **MPP** oferuje znacznie większą skalowalność dzięki rozproszonej pamięci i przesyłowi komunikatów, ale wymaga bardziej skomplikowanego zarządzania. Wybór architektury zależy od specyfiki aplikacji i wymaganej wydajności.