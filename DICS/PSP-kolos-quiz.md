---

### Quiz "Programowanie Sterowników Przemysłowych"

**Instrukcja:** Przeczytaj każde pytanie i wybierz prawidłową odpowiedź (lub odpowiedzi) na podstawie dostarczonych źródeł. Prawidłowe odpowiedzi są podane po pytaniach.

---

**Pytanie 1:** Język LD to diagram drabinkowy.
*   Prawda
*   Fałsz

**Pytanie 2:** Reprezentacja danych procesowych zwykle jest na słowach 32b i bajtach.
*   Prawda
*   Fałsz

**Pytanie 3:** Kontroler można restartować w trybie:
*   a. zawsze Cold/Warm/Hot
*   b. tylko Hot lub Cold
*   c. zależnie od urządzenia Cold/Warm/Hot
*   d. RT HOT Mode
*   e. tylko Warm

**Pytanie 4:** Dopasuj pojęcia:
*   %MD10
*   %QW4
*   %ID0
*   T_SEC
*   %IW0
*   %MW100
    ---
*   Input double WORD
*   Output WORD
*   Input double WORD
*   Jakiś time w S (Some time in S)
*   Input WORD
*   INT or WORD

**Pytanie 5:** W sterownikach jest czasami stosowany kod BCD 12 bitowy.
*   Prawda
*   Fałsz

**Pytanie 6:** Obliczenie pierwiastka kwadratowego lub funkcji trygonometrycznych wymaga modułu koprocesora arytmetycznego.
*   Prawda
*   Fałsz

**Pytanie 7:** Czy liczniki wymagają utworzenia instancji?
*   a. Tak
*   b. Nie

**Pytanie 8:** Reprezentacja pomiaru wielkości fizycznej (np. temperatury) możliwa jest przez:
*   a. zmienną typu BOOL
*   b. zmienną typu REAL
*   c. wskaźnik do struktury
*   d. zmienną typu INT
*   e. zmienną analogową ANALOG

**Pytanie 9:** Każdy sterownik jest wyposażony w lokalne GUI.
*   Prawda
*   Fałsz

**Pytanie 10:** Timery umożliwiają realizacje funkcji:
*   a. filtrowania chwilowych zaburzeń w sygnałach cyfrowych
*   b. zarządzania czasem cyklu PLC
*   c. detekcję zboczy
*   d. odczytu bieżących daty i czasu
*   e. generacji pojedynczego impulsu
*   f. wyłączenia z opóźnieniem
*   g. kumulacji czasu
*   h. załączenia z opóźnieniem

**Pytanie 11:** Bufory (tablice) diagnostyczne przechowują:
*   a. statusy restartów IO
*   b. logi zdarzeń
*   c. statusy aktualizacji IO
*   d. logi błędów i ostrzeżeń

**Pytanie 12:** Możliwe wsparcie dla oprogramowania maszyn sekwencyjnych to:
*   a. SFC
*   b. Dowolne struktury opisujące stan sekwencji
*   c. CFC
*   d. Rozkazy sekwencerów
*   e. Specjalne struktury opisujące stan sekwencji

**Pytanie 13:** Określ przebieg wyjścia timera TOF (OFDT).
*(Opis:* Wyjście Q timera TOF (Turn Off Delay) idzie w stan wysoki, gdy wejście IN jest wysokie, i pozostaje w tym stanie przez zadany czas po tym, jak wejście IN przejdzie w stan niski, a następnie wraca do stanu niskiego. Jeśli wejście IN ponownie stanie się wysokie zanim czas opóźnienia się skończy, timer resetuje swoje odliczanie i pozostaje w stanie wysokim, dopóki IN nie przejdzie ponownie w stan niski i nie upłynie cały czas opóźnienia.)

**Pytanie 14:** Sterowniki działają tylko na liczbach 16 bitowych całkowitych.
*   Prawda
*   Fałsz

**Pytanie 15:** Sterownik może współpracować z lokalnymi lub zdalnymi układami IO.
*   Prawda
*   Fałsz

**Pytanie 16:** Język SFC służy do:
*   a. tworzenia drabinek logicznych
*   b. opisu logiki działań
*   c. opisu sekwencji
*   d. kontroli czasu rzeczywistego
*   e. opisu kombinatoryki układu

**Pytanie 17:** Czy w obu programach działania wynik będzie taki sam? (Dwa przedstawione programy drabinkowe)
*(Opis:* Lewy program: Dwa równoległe obwody (S lub Q) prowadzące do resetowania R, a następnie do wyjścia Q. Prawy program: S i R w szeregu, a następnie Q w szeregu.
Chodzi o porównanie dwóch programów na diagramach, czy wykonują tę samą logikę. Oba programy implementują bramkę logiczną AND (dwa sygnały wejściowe połączone szeregowo) i OR (dwa sygnały wejściowe połączone równolegle), a wynik jest negowany, aby ustawić wyjście Q. Oba programy (drabinkowy i FBD) wizualnie przedstawiają tę samą logikę: `NOT ((%I0.1 OR %I0.2) AND (%M39.0 OR %M39.1))` przypisane do `%Q4.0`.)
*   Prawda
*   Fałsz

**Pytanie 18:** Do programowania PLC można użyć:
*   a. dowolnego narzędzia klasy IDE
*   b. dowolnych narzędzi deweloperskich OpenSource
*   c. uniwersalnych narzędzi IEC61131
*   d. tylko dedykowanych komputerów (np. handheld console)
*   e. dedykowanych narzędzi klasy IDE

**Pytanie 19:** W jakich językach występuje jawna abstrakcja rejestru roboczego (np. akumulatora)?
*   a. FBD & LD
*   b. SFC & CFC
*   c. ST & SCL
*   d. Instruction List
*   e. LD & LAD
*   f. IL & STL

**Pytanie 20:** Czas wykonania programu sterownika jest zawsze stały.
*   Prawda
*   Fałsz

**Pytanie 21:** Rozkazy wykrywania zbocza wymagają:
*   a. użycia przerzutnika RS
*   b. argumentu typu BOOL lub bitu
*   c. dodatkowego bitu stanu
*   d. dodatkowego słowa stanu
*   e. użycia języka graficznego
*   f. użycia przerzutnika T

**Pytanie 22:** Zachowanie stanu:
*   a. bywa konfigurowalne
*   b. jest zbędne
*   c. bywa automatyczne
*   d. w PLC nie występuje
*   e. jest niezbędne

**Pytanie 23:** Co zostanie wykonane przez program?
```
LD alarm2
JMPCN next
LD alarm2_level
ADD 1
ST alarm2_level
next: LD 0
ST iMotorSpeed
```
*   a. gdy alarm2 będzie TRUE to inkrementacja alarm2_level
*   b. dekrementacja alarm2_level
*   c. kod jest błędny
*   d. przypisanie zera do iMotorSpeed
*   e. gdy alarm2 będzie FALSE to inkrementacja alarm2_level

**Pytanie 24:** Typowe formaty adresu to:
*   a. <prefix><memory zone id><index>
*   b. <prefix><memory zone id><prefix><index>
*   c. <prefix><memory zone id><index><suffix>
*   d. <memory zone id><index>.[<index>]
*   e. <index><at><memory zone id>

**Pytanie 25:** Określ przebieg wyjścia zwykłego timera TP.
*(Opis:* Wyjście Q timera TP (Pulse Timer) idzie w stan wysoki na narastającym zboczu wejścia IN i pozostaje w tym stanie przez zadany czas, niezależnie od stanu wejścia IN. Jeśli wejście IN przejdzie w stan wysoki ponownie, gdy wyjście Q jest jeszcze wysokie, timer zresetuje swoje odliczanie i rozpocznie nowy puls.)

**Pytanie 26:** Każdy sterownik ma RTC.
*   Prawda
*   Fałsz

**Pytanie 27:** Program wgrany i wykonywany w sterowniku może być:
*   a. interpretowanym pseudokodem
*   b. skompilowanym kodem maszynowym (binary code)
*   c. kodem BCD lub Excess-3
*   d. wyłącznie kodem maszynowym (kod binarny)
*   e. programowalną sekwencją przekaźników 24V

**Pytanie 28:** Rozkazy liczników programowych w zakresie podstawowym służą do:
*   a. odmierzania upływu czasu
*   b. zliczania tzw. zboczy
*   c. zliczania cykli programowych
*   d. zliczania zmian stanu zmiennych BOOL
*   e. liczenia błędów RT
*   f. kontroli czasu rzeczywistego

**Pytanie 29:** W językach graficznych występują rozkazy skoków.
*   Prawda
*   Fałsz

**Pytanie 30:** Coils oraz Contacts to elementy:
*   a. kondensatorów i induktorów logicznych
*   b. języka LAD
*   c. przekaźników IO
*   d. zapisu w IL
*   e. języka LD
*   f. języka FBD

**Pytanie 31:** POU mogą posiadać interfejs:
*   a. OUTPUT
*   b. INPUT
*   c. GUI
*   d. NET
*   e. INOUT

**Pytanie 32:** Rozkazy komparatorów służą do:
*   a. komparacji pojedynczych bitów
*   b. porównywania wartości liczb
*   c. testowania relacji pomiędzy zmiennymi tylko o typach liczbowych
*   d. porównywania zawartości słów
*   e. Obliczania uzupełnień w obliczeniach BCD
*   f. komparacji obrazów IO

**Pytanie 33:** Czy timery wymagają utworzenia instancji?
*   a. Nie
*   b. Tak

**Pytanie 34:** FBD to język graficzny.
*   Prawda
*   Fałsz

**Pytanie 35:** Może być wykorzystane słabe typowanie.
*   Prawda
*   Fałsz

**Pytanie 36:** Wymiana danych CPU (pamięć) z modułami sieciowymi jest zadaniem realizowanym w tle.
*   Prawda
*   Fałsz

**Pytanie 37:** PLC charakteryzuje się:
*   a. podwyższoną odpornością na błędy kodowania
*   b. podwyższoną wydajnością przetwarzania danych
*   c. pracą w RT
*   d. podwyższoną odpornością na czynniki EMC
*   e. podwyższoną odpornością na warunki środowiskowe
*   f. pracą w NRT

**Pytanie 38:** Określ przebieg wyjścia timera TON (ODT).
*(Opis:* Wyjście Q timera TON (Turn On Delay) idzie w stan wysoki dopiero po upływie zadanego czasu od momentu, gdy wejście IN przejdzie w stan wysoki i pozostaje w nim. Jeśli wejście IN przejdzie w stan niski zanim czas opóźnienia się skończy, wyjście Q nigdy nie przejdzie w stan wysoki.)

**Pytanie 39:** Może być użyte mocne typowanie.
*   Prawda
*   Fałsz

**Pytanie 40:** Timery można użyć (są dedykowane dla):
*   a. tylko w językach graficznych
*   b. w językach graficznych i tekstowych
*   c. w LD, ST, FBD, IL
*   d. tylko w języku LD
*   e. tylko w językach tekstowych

**Pytanie 41:** Sterownik przemysłowy to:
*   a. rodzaj komputera do realizacji zadań IT w fabryce
*   b. rodzaj komputera do realizacji zadań sterowania
*   c. system sprzętowo-programowy RT
*   d. komputer dedykowany do obsługi zadań dyskretnych
*   e. zestaw programowalnych przekaźników

**Pytanie 42:** Sterowniki przemysłowe są na pokładach sond kosmicznych.
*   Prawda
*   Fałsz

**Pytanie 43:** Wykrywanie zboczy dotyczy:
*   a. wykrycia zmiany stanu zmiennej INT
*   b. wykrycia przekroczenia wartości gradientu sygnału analogowego
*   c. wykrycia zboczy zorbingowych
*   d. wykrycia wahań napięcia w kontrolerze
*   e. wykrycia narastających lub opadających zboczy sygnałów logicznych
*   f. wykrycia zmiany stanu zmiennej BOOL

**Pytanie 44:** Standardowe (IEC61131) języki programowania PLC to:
*   a. Pascal (ISO 7185)
*   b. C, Lisp, Prolog i R
*   c. LB, SD, SVC
*   d. LD, IL, FBD, CFC
*   e. Ladder Diagram, Instruction List
*   f. LD, ST, SFC
*   g. FBD, SFC, LD
*   h. ANSI C

**Pytanie 45:** Kontrolery przemysłowe charakteryzują się:
*   a. pracą w czasie rzeczywistym
*   b. pracą w Hard RT lub Firm RT
*   c. dużą szybkością działania
*   d. wykonywaniem dowolnych zadań w ściśle określonym czasie
*   e. niską szybkością działania

**Pytanie 46:** Napisz fragment poprawnego programu (w języku listy instrukcji).
*(Oczekiwany kod: )*
**LD %IW4**
**SUB 16#A**
**ST %MW20**

**Pytanie 47:** Typowe wsparcie dla programisty PLC to:
*   a. rozkazy tworzenia modeli dla uczenia maszynowego
*   b. liczniki i timery
*   c. logic flow
*   d. detekcja pól magnetycznych i EM
*   e. zewnętrzne sprzężenia neuralinkowe
*   f. logika bitowa i słowowa
*   g. wykrywanie zboczy
*   h. wykrywanie zaników

**Pytanie 48:** POU jako Bloki Funkcyjne wymagają utworzenia ich instancji.
*   Prawda
*   Fałsz

**Pytanie 49:** Algorytmy sterowania zwykle wymagają pamięci RAM o rozmiarze przynajmniej 32GB.
*   Prawda
*   Fałsz

**Pytanie 50:** Jaka jest nazwa przedmiotu z którego piszesz kolokwium?
*   a. Projektowanie Sterowników Przemysłowych
*   b. Projektowanie Standardów Przemysłowych
*   c. Programowanie Przemysłowych Sterowników Logicznych
*   d. Programowanie Sterowników Czasu Rzeczywistego
*   e. Programowanie Sterowników Przemysłowych

**Pytanie 51:** W sterownikach S7-300 stosowany jest kod BCD 12 bitowy.
*   Prawda
*   Fałsz

**Pytanie 52:** W sterownikach S7-1500 stosowany kod BCD 10 bitowy.
*   Prawda
*   Fałsz

**Pytanie 53:** Watchdog służy do:
*   a. odmierzania czasu do zatrzymania programu
*   b. odmierzania czasu do uruchomienia programu
*   c. obsługi przerwań od układów IO
*   d. kontroli czasu realizacji komunikacji z CPU
*   e. kontroli czasu wykonywania programów
*   f. kontroli czy program nie wykonuje się dłużej niż zakładano

**Pytanie 54:** Rozkazy LD operują tylko na zmiennych typu bool.
*   Prawda
*   Fałsz

**Pytanie 55:** Język LAD to diagram połączeń macierzowych (linkup array diagram) znany jako cause-effect matrix CEM.
*   Prawda
*   Fałsz

**Pytanie 56:** Napisz fragment poprawnego programu wywołania timera w języku ST.
*(Oczekiwany kod: )*
```
PROGRAM kolo
VAR
    myTimer : TON ;
    timCurrentValue : TIME ;
    bAlarm : AT %QX0.0 : BOOL ;
    bStart : BOOL;
END_VAR

myTimer (IN := bStart, PT := T#5s);
bAlarm := myTimer.Q ;
timCurrentValue := myTimer.ET;
```

**Pytanie 57:** Odczyt/zapis fizycznych układów IO jest możliwy w trakcie wykonywania programu.
*   Prawda
*   Fałsz

**Pytanie 58:** Napisz fragment poprawnego programu wywołania Licznika w języku ST.
*(Oczekiwany kod: )*
```
PROGRAM kolo
VAR
    myCounter : CTU ;
    cntCurrentValue : INT ;
    bOvf : AT %QX7.3 : BOOL ;
    bCount : BOOL;
    bReset : BOOL;
END_VAR

myCounter (RESET := bReset, PV := 15);
bOvf := myCounter.Q ;
cntCurrentValue := myCounter.CV;
```

**Pytanie 59:** POU jako Funkcje wymagają utworzenia ich instancji.
*   Prawda
*   Fałsz

**Pytanie 60:** GE: Obszary lokalne pamięci bloku mogą być dziedziczone.
*   Prawda
*   Fałsz

**Pytanie 61:** Języki programowania PLC dzielą się na:
*   a. strukturalne nisko i wysokopoziomowe
*   b. assemblerowe i logiczne
*   c. tekstowe i graficzne
*   d. obiektowe i agentowe
*   e. opisu sekwencji

**Pytanie 62:** Reprezentacja danych procesowych zwykle jest na słowach 16b i bitach.
*   Prawda
*   Fałsz

**Pytanie 63:** Czy programy obliczają to samo? (Dwa programy logiczne, drabinkowy i FBD, przedstawiające tę samą logikę: `NOT ((%I0.1 OR %I0.2) AND (%M39.0 OR %M39.1))` do `%Q4.0`.)
*   Prawda
*   Fałsz

**Pytanie 64:** Dialog z modułami funkcyjnymi jest realizowany w cyklu aplikacyjnym.
*   Prawda
*   Fałsz

**Pytanie 65:** Konfiguracja kontrolera dotyczy:
*   a. konfiguracji i parametryzacji zasobów
*   b. sposobu montażu komponentów
*   c. instalacji wymaganych frameworków i pakietów
*   d. doboru rodzaju komponentów dla aplikacji
*   e. umiejscowienia modułów w magistrali systemowej (w raku)

**Pytanie 66:** Typowe moduły sterowników to:
*   a. Wejścia i wyjścia czterostanowe
*   b. Wejścia i wyjścia analogowe
*   c. moduły archiwizacyjne
*   d. koprocesory funkcyjne np. szybkie liczniki
*   e. Wejścia i wyjścia dwustanowe
*   f. Izochroniczne moduły holocronowe
*   g. Komunikacja w paśmie Sub-Etha
*   h. koprocesory sieciowe

**Pytanie 67:** Dopasuj języki do ich typu i przykładowych elementów:
*   LAD
*   LD
*   IL
*   ST
*   VHDL
*   FBD
*   CFC
*   STL
*   SFC
    ---
*   graphic (contacts, coils, blocks, wires, %IX0.0...)
*   text (LD, ST, AND, XOR, OR, ADD, ...)
*   text (IF, WHILE, SELECT, ...)
*   graphic (blocks, wires, data flow, diagram, ...)
*   graphic (blocks, wires, direct feedbacks, exec...)

**Pytanie 68:** Liczniki sygnalizują na wyjściach (zależnie od platformy):
*   a. wartość licznika z poprzedniego cyklu
*   b. przepełnienie logiczne
*   c. osiągnięcie przepełnienia pojemności
*   d. wartość oczekiwanego czasu zliczania
*   e. wartość bieżącą licznika
*   f. osiągnięcie stanu zero

---

### Klucz odpowiedzi:

**Pytanie 1:** Prawda
**Pytanie 2:** Fałsz
**Pytanie 3:** c. zależnie od urządzenia Cold/Warm/Hot
**Pytanie 4:**
*   %MD10 -> Input double WORD
*   %QW4 -> Output WORD
*   %ID0 -> Input double WORD
*   T_SEC -> Jakiś time w S (Some time in S)
*   %IW0 -> Input WORD
*   %MW100 -> INT or WORD
**Pytanie 5:** Prawda
**Pytanie 6:** Fałsz
**Pytanie 7:** a. Tak
**Pytanie 8:** b. zmienną typu REAL, d. zmienną typu INT
**Pytanie 9:** Fałsz
**Pytanie 10:** a. filtrowania chwilowych zaburzeń w sygnałach cyfrowych, c. detekcję zboczy, e. generacji pojedynczego impulsu, f. wyłączenia z opóźnieniem, h. załączenia z opóźnieniem
**Pytanie 11:** b. logi zdarzeń, d. logi błędów i ostrzeżeń
**Pytanie 12:** a. SFC, b. Dowolne struktury opisujące stan sekwencji, d. Rozkazy sekwencerów
**Pytanie 13:** Wyjście Q timera TOF (Turn Off Delay) jest **wysokie, gdy IN jest wysokie, i pozostaje wysokie przez zadany czas po tym, jak IN przejdzie w stan niski**, następnie wraca do niskiego. Jeśli IN ponownie stanie się wysokie zanim czas opóźnienia się skończy, timer resetuje swoje odliczanie.
**Pytanie 14:** Fałsz
**Pytanie 15:** Prawda
**Pytanie 16:** c. opisu sekwencji
**Pytanie 17:** Fałsz (Niestety, na podstawie dostarczonych diagramów, programy nie są takie same. Jeden z diagramów (drabinkowy) pokazuje resetowanie wyjścia Q, podczas gdy drugi (FBD) przedstawia negację logiczną złożonego wyrażenia).
**Pytanie 18:** c. uniwersalnych narzędzi IEC61131
**Pytanie 19:** d. Instruction List, f. IL & STL
**Pytanie 20:** Fałsz
**Pytanie 21:** b. argumentu typu BOOL lub bitu, c. dodatkowego bitu stanu
**Pytanie 22:** a. bywa konfigurowalne, c. bywa automatyczne, e. jest niezbędne
**Pytanie 23:** a. gdy alarm2 będzie TRUE to inkrementacja alarm2_level, d. przypisanie zera do iMotorSpeed
**Pytanie 24:** a. <prefix><memory zone id><index>, d. <memory zone id><index>.[<index>]
**Pytanie 25:** Wyjście Q timera TP (Pulse Timer) idzie w stan wysoki na **narastającym zboczu wejścia IN i pozostaje w tym stanie przez zadany czas**, niezależnie od tego, jak długo IN jest wysokie. Jeśli IN ma kolejne narastające zbocze, gdy Q jest nadal wysokie, timer wygeneruje nowy puls.
**Pytanie 26:** Fałsz
**Pytanie 27:** a. interpretowanym pseudokodem
**Pytanie 28:** a. odmierzania upływu czasu, b. zliczania tzw. zboczy, d. zliczania zmian stanu zmiennych BOOL, e. liczenia błędów RT
**Pytanie 29:** Prawda
**Pytanie 30:** b. języka LAD, e. języka LD
**Pytanie 31:** a. OUTPUT, b. INPUT, c. GUI
**Pytanie 32:** b. porównywania wartości liczb, d. porównywania zawartości słów
**Pytanie 33:** b. Tak
**Pytanie 34:** Prawda
**Pytanie 35:** Prawda
**Pytanie 36:** Fałsz
**Pytanie 37:** c. pracą w RT, d. podwyższoną odpornością na czynniki EMC, e. podwyższoną odpornością na warunki środowiskowe
**Pytanie 38:** Wyjście Q timera TON (Turn On Delay) idzie w stan wysoki **dopiero po upływie zadanego czasu od momentu, gdy wejście IN przejdzie w stan wysoki i pozostaje w nim**. Jeśli IN przejdzie w stan niski zanim czas opóźnienia się skończy, wyjście Q nigdy nie przejdzie w stan wysoki.
**Pytanie 39:** Prawda
**Pytanie 40:** b. w językach graficznych i tekstowych, c. w LD, ST, FBD, IL
**Pytanie 41:** a. rodzaj komputera do realizacji zadań IT w fabryce, b. rodzaj komputera do realizacji zadań sterowania, c. system sprzętowo-programowy RT, e. zestaw programowalnych przekaźników
**Pytanie 42:** Fałsz
**Pytanie 43:** b. wykrycia przekroczenia wartości gradientu sygnału analogowego, d. wykrycia wahań napięcia w kontrolerze, e. wykrycia narastających lub opadających zboczy sygnałów logicznych, f. wykrycia zmiany stanu zmiennej BOOL
**Pytanie 44:** d. LD, IL, FBD, CFC, e. Ladder Diagram, Instruction List, f. LD, ST, SFC, g. FBD, SFC, LD
**Pytanie 45:** a. pracą w czasie rzeczywistym
**Pytanie 46:**
```
LD %IW4
SUB 16#A
ST %MW20
```

**Pytanie 47:** b. liczniki i timery, c. logic flow, f. logika bitowa i słowowa, g. wykrywanie zboczy
**Pytanie 48:** Prawda
**Pytanie 49:** Fałsz
**Pytanie 50:** e. Programowanie Sterowników Przemysłowych
**Pytanie 51:** Prawda
**Pytanie 52:** Fałsz
**Pytanie 53:** e. kontroli czasu wykonywania programów, f. kontroli czy program nie wykonuje się dłużej niż zakładano
**Pytanie 54:** Fałsz
**Pytanie 55:** Fałsz
**Pytanie 56:**
```
PROGRAM kolo
VAR
    myTimer : TON ;
    timCurrentValue : TIME ;
    bAlarm : AT %QX0.0 : BOOL ;
    bStart : BOOL;
END_VAR

myTimer (IN := bStart, PT := T#5s);
bAlarm := myTimer.Q ;
timCurrentValue := myTimer.ET;
```

**Pytanie 57:** Prawda
**Pytanie 58:**
```
PROGRAM kolo
VAR
    myCounter : CTU ;
    cntCurrentValue : INT ;
    bOvf : AT %QX7.3 : BOOL ;
    bCount : BOOL;
    bReset : BOOL;
END_VAR

myCounter (RESET := bReset, PV := 15);
bOvf := myCounter.Q ;
cntCurrentValue := myCounter.CV;
```

**Pytanie 59:** Fałsz
**Pytanie 60:** Prawda
**Pytanie 61:** a. strukturalne nisko i wysokopoziomowe, c. tekstowe i graficzne, e. opisu sekwencji
**Pytanie 62:** Prawda
**Pytanie 63:** Prawda
**Pytanie 64:** Prawda
**Pytanie 65:** a. konfiguracji i parametryzacji zasobów, e. umiejscowienia modułów w magistrali systemowej (w raku)
**Pytanie 66:** b. Wejścia i wyjścia analogowe, d. koprocesory funkcyjne np. szybkie liczniki, e. Wejścia i wyjścia dwustanowe, h. koprocesory sieciowe
**Pytanie 67:**
*   LAD -> graphic (contacts, coils, blocks, wires, %IX0.0...)
*   LD -> graphic (contacts, coils, blocks, wires, %IX0.0...)
*   IL -> text (LD, ST, AND, XOR, OR, ADD, ...)
*   ST -> text (IF, WHILE, SELECT, ...)
*   VHDL -> *Brak poprawnego dopasowania w źródłach, zaznaczone jako nieprawidłowe w quizie źródłowym.*
*   FBD -> graphic (blocks, wires, data flow, diagram, ...)
*   CFC -> graphic (blocks, wires, direct feedbacks, exec...)
*   STL -> *Brak poprawnego dopasowania w źródłach, zaznaczone jako nieprawidłowe w quizie źródłowym dla elementów ST.*
*   SFC -> *Brak poprawnego dopasowania w źródłach, zaznaczone jako nieprawidłowe w quizie źródłowym dla elementów CFC/FBD.*
**Pytanie 68:** c. osiągnięcie przepełnienia pojemności, e. wartość bieżącą licznika, f. osiągnięcie stanu zero

---