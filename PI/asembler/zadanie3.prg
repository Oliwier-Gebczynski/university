POB A ( pobierz wartosc a do akumulatora )
ŁAD A1  (zaladowanie wartosci akumulatora do miejsca w pamieci wskazanego przez argument )

petla1:   POB B 
          ODE jeden
          SOZ koniec
          ŁAD B
          POB A
          DNS
          DNS
          POB A1
          DNS
          SDP mnozenie
          PZS
          ŁAD A
          SOB petla1

mnozenie: PZS
          ŁAD licznik
          PZS
          ŁAD C
          PZS
          ŁAD A2
          PZS
          ŁAD A3

petla2:   POB C
          ODE jeden
          SOZ zero
          ŁAD C
          POB A2
          DOD A3
          ŁAD A2
          SOB petla2  

zero:     POB A2
          DNS
          POB licznik
          DNS
          PWR

koniec:   POB A
          STP

A:        RST 3
B:        RST 4
A1:       RPA
A2:       RPA
A3:       RPA
C:        RST 0
jeden:    RST 1
licznik:  RPA

-----------------------------------------------------------------

POB A ( pobierz wartosc a do akumulatora )
ŁAD A1  (zaladowanie wartosci akumulatora do miejsca w pamieci wskazanego przez argument )

petla1:   POB B 
          ODE jeden
          SOZ koniec
          ŁAD B
          POB A
          DNS
          DNS
          POB A1
          DNS
          SDP mnozenie
          PZS
          ŁAD A
          SOB petla1

mnozenie: PZS
          ŁAD licznik
          PZS
          ŁAD C
          PZS
          ŁAD A2
          PZS
          ŁAD A3

petla2:   POB C
          ODE jeden
          SOZ zero
          ŁAD C
          POB A2
          DOD A3
          ŁAD A2
          SOB petla2  

zero:     POB A2
          DNS
          POB licznik
          DNS
          PWR

koniec:   POB A
          STP

A:        RST 3
B:        RST 4
A1:       RPA
A2:       RPA
A3:       RPA
C:        RST 0
jeden:    RST 1
licznik:  RPA

-------------------------------------------------------
POB A 
ŁAD A1
ŁAD A2

petla1: POB B
        ODE jeden
        SOZ koniec
        ŁAD B
        POB A2
        ODE jeden
        ŁAD C
        DNS
        POB A1
        DNS 
        POB A
        DNS
        SDP mnozenie
        PZS 
        ŁAD A
        SOB petla1

mnozenie:   PZS
            ŁAD A
            PZS
            ŁAD A1
            PZS
            ŁAD C
            SDP petla2

petla2: POB C
        ODE jeden
        SOZ zero
        ŁAD C
        POB A 
        DOD A1
        SOB petla2

zero:   POB A1
        DNS
        POB licznik
        DNS
        PWR

koniec: POB A 
        STP

A:       RST 3
B:       RST 3
A1:      RPA
A2:      RPA
C:       RST 0
jeden:   RST 1
licznik: RPA 


