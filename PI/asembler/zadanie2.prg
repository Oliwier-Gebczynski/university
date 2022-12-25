--- Wyznaczyć najmniejszy element w N-elementowej tablicy TAB podanej w pamięci -----

POB n   (pobranie do ak wartosci n)
SOZ koniec      (jezeli n = 0 to przejdz na koniec)
POB tab (pobranie do ak wartosci 4)
ŁAD wynik (zaladowanie zawartosci ak do wskazanej komórki pamieci)

petla:  POB n (do ak pobierz n)
        ODE jeden (odejmij od ak 1)
        SOM koniec ( jezeli ak < 0 to skok do koniec)
        ŁAD n   (zapisz ak do wskazaniej komórki przez argument)

rozkaz: POB tab 
        ODE wynik
        SOM wieksze 
        POB rozkaz
        DOD jeden      ( zwiekszenie o jeden adresu rozkazu, żeby brało kolejny element )
        ŁAD rozkaz 
        SOB petla

wieksze:    DOD wynik
            ŁAD wynik
            POB rozkaz 
            DOD jeden
            ŁAD rozkaz
            SOB petla

koniec: POB wynik
        STP

n: RST 4
tab:    RST 4
        RST 1
        RST 6
        RST 2
jeden: RST 1
wynik: RPA

----------------------------------------------------------------------

POB n   
SOZ koniec      
POB tab 
ŁAD wynik 

petla:  POB n 
        ODE jeden 
        SOM koniec 
        ŁAD n   

rozkaz: POB tab 
        ODE wynik
        SOM wieksze 
        POB rozkaz
        DOD jeden
        ŁAD rozkaz 
        SOB petla

wieksze:    DOD wynik
            ŁAD wynik
            POB rozkaz 
            DOD jeden
            ŁAD rozkaz
            SOB petla

koniec: POB wynik
        STP

n: RST 4
tab:    RST 4
        RST 1
        RST 6
        RST 2
jeden: RST 1
wynik: RPA