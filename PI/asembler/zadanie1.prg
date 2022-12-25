----   Obliczyć NWD    -----

petla:  POB a ( pobierz do ak wartość wskazana przez a )
        ODE b  ( odejmij od ak wartosc wskazana przez b )
        SOZ koniec ( jezeli ak = 0 wtedy skok pod wskazany adres (koniec))
        SOM else ( jezeli ak < 0 to wtedy skok pod wskazany adres (else))
        ŁAD a ( załadowanie zawartosci ak pod wskazany adres (a))
        SOB petla (petla od poczatku)
else:    
        POB b   ( pobierz do ak wartosc wskazana przez b)
        ODE a   ( odejmij od ak wartosc wskazana przez a )
        ŁAD b   ( zapisanie zawartosci ak pod wsazany adres (b))
        SOB petla   (skok do poczatku petli)
koniec:
        POB a (pobranie do ak zawartosci a)
        STP ( zakonczenie programu )
a: RST 12   ( stworzenie miejsca w pamieci i wpisanie tam wartosci )
b: RST 16

========================================================================

petla:  POB a
        ODE b
        SOZ koniec
        SOM else
        ŁAD a
        SOB petla
else:    
        POB b
        ODE a
        ŁAD b
        SOB petla
koniec:
        POB a
        STP
a: RST 12
b: RST 16