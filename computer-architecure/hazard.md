### Hazard danych i sterowania

Hazard to problem występujący w procesorach potokowych, gdy następuje zakłócenie w harmonogramie wykonywania rozkazów. Może być związany z danymi lub sterowaniem i prowadzić do spadku wydajności oraz konieczności stosowania technik naprawczych.

---

### **1. Hazard danych**

Hazard danych występuje, gdy między rozkazami zachodzi zależność danych, co powoduje konflikt podczas ich wykonywania.

#### **Rodzaje hazardu danych:**
1. **Zależność prawdziwa (RAW - Read After Write):**
   - Rozkaz odczytuje dane, które są jeszcze niegotowe, ponieważ wcześniejszy rozkaz nie zakończył ich zapisu.
   - Przykład: Instrukcja B potrzebuje wyniku z instrukcji A, która jeszcze nie została ukończona.

2. **Antyzależność (WAR - Write After Read):**
   - Rozkaz zapisuje dane, zanim poprzedni rozkaz zakończy ich odczyt.

3. **Zależność wyjściowa (WAW - Write After Write):**
   - Dwa rozkazy próbują zapisać różne wyniki do tego samego rejestru w niewłaściwej kolejności.

#### **Rozwiązania hazardu danych:**
1. **Przemianowanie rejestrów (Register Renaming):**
   - Nadanie unikalnych nazw rejestrom w celu uniknięcia konfliktów między rozkazami.
   - Efektywnie eliminuje antyzależności i zależności wyjściowe.

2. **Wyprzedzające pobieranie argumentów (Operand Forwarding):**
   - Wynik z jednego etapu potoku jest przekazywany bezpośrednio do kolejnego, zanim zostanie zapisany do rejestru.

3. **Wstrzymanie potoku (Stalling):**
   - Procesor wstrzymuje wykonywanie rozkazów do czasu rozwiązania konfliktu.

---

### **2. Hazard sterowania**

Hazard sterowania występuje, gdy procesor nie może określić, które rozkazy wykonać, ponieważ pojawia się rozgałęzienie (np. instrukcja skoku lub warunkowa). Jest to szczególnie problematyczne w procesorach potokowych.

#### **Przyczyny hazardu sterowania:**
- Procesor nie wie, który blok instrukcji wykonać, dopóki nie zostanie określony wynik warunku (np. w instrukcjach `if` lub `goto`).

#### **Rozwiązania hazardu sterowania:**
1. **Przewidywanie rozgałęzień (Branch Prediction):**
   - Procesor "zgaduje", które rozgałęzienie zostanie wykonane, i kontynuuje przetwarzanie, zanim wynik warunku będzie znany.
   - Przykład: Dynamiczne strategie przewidywania oparte na historii poprzednich rozgałęzień.

2. **Tablice historii rozgałęzień (Branch History Tables):**
   - Tablica przechowuje informacje o poprzednich rozgałęzieniach i pomaga przewidzieć ich wynik w przyszłości.

3. **Technika skoków opóźnionych (Delayed Branch):**
   - Procesor wykonuje kilka instrukcji następujących po skoku, zanim nastąpi rzeczywista zmiana kierunku wykonywania.

---

### **Podsumowanie**

| **Rodzaj hazardu**   | **Przyczyna**                             | **Rozwiązania**                          |
|-----------------------|-------------------------------------------|-------------------------------------------|
| **Hazard danych**     | Zależności między danymi w rozkazach      | Przemianowanie rejestrów, operand forwarding |
| **Hazard sterowania** | Rozgałęzienia w programie                 | Przewidywanie rozgałęzień, tablice historii rozgałęzień |

---

### **Dlaczego to ważne?**
Hazard jest kluczowym problemem w nowoczesnych procesorach potokowych, który może prowadzić do nieefektywnego wykorzystania zasobów procesora. Stosowanie zaawansowanych technik, takich jak przewidywanie rozgałęzień czy przemianowanie rejestrów, pozwala znacząco zwiększyć wydajność systemów komputerowych.