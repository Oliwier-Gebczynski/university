### Protokół MESI

Protokół MESI (Modified, Exclusive, Shared, Invalid) to mechanizm używany w systemach wieloprocesorowych z pamięcią współdzieloną, takich jak **SMP (Symmetric Multiprocessing)**. Jego głównym celem jest zapewnienie **spójności pamięci podręcznych** w procesorach, które korzystają z tej samej pamięci operacyjnej.

---

### **Główne założenia protokołu MESI**

1. **Spójność pamięci podręcznych:**
   - Każdy procesor w systemie wieloprocesorowym ma swoją pamięć podręczną (cache), w której przechowuje kopie danych z pamięci głównej.
   - Protokół MESI zapobiega sytuacjom, w których różne procesory widzą różne wersje tych samych danych.

2. **Współdziałanie między procesorami:**
   - MESI synchronizuje dostęp do pamięci podręcznej procesorów, monitorując stan każdej linii danych.

---

### **Stany protokołu MESI**

Każda linia danych w pamięci podręcznej może znajdować się w jednym z czterech stanów:

1. **Modified (M):**
   - Dane w pamięci podręcznej zostały zmodyfikowane.
   - Te dane różnią się od wersji w pamięci głównej (są "brudne").
   - Tylko jeden procesor może przechowywać dane w stanie **Modified**.

2. **Exclusive (E):**
   - Dane znajdują się w pamięci podręcznej i są identyczne jak w pamięci głównej.
   - Tylko jeden procesor przechowuje te dane, co pozwala na szybki zapis bez konieczności synchronizacji.

3. **Shared (S):**
   - Dane znajdują się w pamięciach podręcznych kilku procesorów.
   - Są identyczne z danymi w pamięci głównej.
   - Modyfikacja tych danych wymaga zmiany stanu na **Invalid** w innych pamięciach podręcznych.

4. **Invalid (I):**
   - Dane w pamięci podręcznej są nieważne, ponieważ zostały zmodyfikowane przez inny procesor lub usunięte.

---

### **Działanie protokołu MESI**

1. **Odczyt danych:**
   - Jeśli dane są w stanie **Invalid**, procesor pobiera je z pamięci głównej lub innej pamięci podręcznej.
   - W zależności od liczby kopii, stan może przejść na **Exclusive** (jedyna kopia) lub **Shared** (wiele kopii).

2. **Zapis danych:**
   - Jeśli procesor chce zmodyfikować dane, musi zmienić ich stan na **Modified**.
   - Inne procesory, które przechowują te dane w stanie **Shared** lub **Exclusive**, zmieniają stan na **Invalid**.

---

### **Przykład działania**

1. Procesor A ładuje dane z pamięci głównej – linia danych przechodzi w stan **Exclusive**.
2. Procesor B również chce odczytać te dane – stan zmienia się na **Shared** w obu procesorach.
3. Procesor B modyfikuje dane – stan w procesorze B zmienia się na **Modified**, a w procesorze A na **Invalid**.

---

### **Zalety protokołu MESI**

1. **Efektywne wykorzystanie pamięci podręcznej:**
   - Protokół minimalizuje liczbę dostępu do pamięci głównej, co zwiększa wydajność.

2. **Zapewnienie spójności:**
   - Gwarantuje, że wszystkie procesory pracują na tej samej wersji danych.

3. **Zoptymalizowany dostęp:**
   - Dzięki stanom **Exclusive** i **Shared**, dane mogą być szybko dostępne bez zbędnych operacji na pamięci głównej.

---

### **Podsumowanie**

Protokół MESI jest kluczowym mechanizmem w systemach wieloprocesorowych, który zapewnia spójność pamięci podręcznych przy jednoczesnym maksymalnym wykorzystaniu ich wydajności. Dzięki prostemu, ale skutecznemu modelowi stanów (**Modified, Exclusive, Shared, Invalid**), MESI pozwala na synchronizację i optymalizację współdzielenia danych między procesorami.