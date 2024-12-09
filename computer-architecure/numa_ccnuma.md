### Architektury NUMA i CC-NUMA

Architektury NUMA i CC-NUMA to modele pamięci stosowane w systemach wieloprocesorowych, które optymalizują dostęp do pamięci operacyjnej w celu zwiększenia wydajności. Oba podejścia bazują na pamięci rozproszonej fizycznie, ale logicznie wspólnej, co pozwala na skalowalność i elastyczność systemów.

---

### **1. NUMA (Non-Uniform Memory Access)**

NUMA to architektura, w której pamięć operacyjna jest fizycznie podzielona między różne węzły (procesory), ale wszystkie procesory mają do niej logicznie wspólny dostęp.

#### **Charakterystyka:**
1. **Pamięć fizycznie rozproszona, ale logicznie wspólna:**
   - Każdy procesor ma lokalny fragment pamięci, do którego dostęp jest szybszy.
   - Procesor może również uzyskać dostęp do pamięci innych procesorów, ale jest to wolniejsze.

2. **Dostęp do pamięci:**
   - **Lokalny dostęp:** Szybszy, ponieważ procesor korzysta z własnej, przypisanej pamięci.
   - **Zdalny dostęp:** Wolniejszy, ponieważ wymaga komunikacji między węzłami.

3. **Zastosowania:**
   - Systemy NUMA są wykorzystywane w dużych serwerach i superkomputerach, gdzie wymagana jest wysoka skalowalność.

---

### **2. CC-NUMA (Cache-Coherent NUMA)**

CC-NUMA (Cache-Coherent Non-Uniform Memory Access) to rozszerzenie NUMA, które zapewnia spójność pamięci podręcznej w całym systemie.

#### **Charakterystyka:**
1. **Utrzymanie spójności pamięci cache:**
   - Procesory w systemie CC-NUMA mogą przechowywać kopie tych samych danych w swoich pamięciach podręcznych.
   - Spójność między pamięciami podręcznymi jest utrzymywana za pomocą katalogów i protokołów (np. MESI).

2. **Katalog:**
   - Centralny mechanizm zarządzający, który monitoruje stan każdej linii pamięci w całym systemie.
   - Katalog zapisuje informacje o tym, które procesory przechowują kopie danej linii danych.

3. **Optymalizacja dostępu:**
   - Procesor w pierwszej kolejności korzysta z danych lokalnych.
   - Jeśli dane są w pamięci podręcznej innego procesora, są przesyłane bezpośrednio między węzłami, co jest szybsze niż dostęp do pamięci głównej.

---

### **Porównanie NUMA i CC-NUMA**

| **Cecha**                      | **NUMA**                                       | **CC-NUMA**                                   |
|--------------------------------|------------------------------------------------|----------------------------------------------|
| **Spójność pamięci podręcznej** | Brak mechanizmu utrzymania spójności           | Spójność zapewniana za pomocą katalogów       |
| **Dostęp do pamięci**           | Nielogiczny podział między lokalnym a zdalnym  | Optymalizowany za pomocą zarządzania cache    |
| **Skalowalność**                | Wysoka                                         | Bardzo wysoka dzięki spójności cache         |
| **Zastosowania**                | Serwery i superkomputery                       | Rozproszone systemy wieloprocesorowe          |

---

### **Przykład działania w CC-NUMA**

1. Procesor A modyfikuje dane i zapisuje je w swojej pamięci podręcznej. 
2. Katalog aktualizuje informację o zmianie, oznaczając dane w innych pamięciach podręcznych jako nieaktualne.
3. Procesor B, który chce uzyskać dostęp do tych danych, może je pobrać z pamięci lokalnej procesora A, zamiast odwoływać się do pamięci głównej.

---

### **Zalety architektur NUMA i CC-NUMA**

1. **NUMA:**
   - Zwiększa szybkość przetwarzania danych lokalnych.
   - Skalowalność pozwala na budowę systemów z dużą liczbą procesorów.

2. **CC-NUMA:**
   - Rozwiązuje problem niespójności pamięci podręcznej.
   - Daje lepszą wydajność w zastosowaniach wieloprocesorowych dzięki efektywnemu zarządzaniu danymi.

---

### **Podsumowanie**

NUMA i CC-NUMA to architektury pamięci stworzone do pracy z wieloma procesorami w dużych systemach obliczeniowych. NUMA koncentruje się na optymalizacji lokalnego dostępu do pamięci, podczas gdy CC-NUMA dodatkowo zapewnia spójność pamięci podręcznej, co czyni ją bardziej zaawansowaną i wydajną w rozproszonych systemach wieloprocesorowych.