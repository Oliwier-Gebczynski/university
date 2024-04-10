select nr_sali from rozklady
where nr_przedm = 20;

select nazwisko from studenci
where nazwisko like 'M%';

select nazwa_przedm from przedmioty
join pracownicy on nr_prac=nr_odp_prac
where pracownicy.nazwisko = 'Janeczek';

select nr_sali from rozklady
where godzina < 14
order by godzina;

select przedmioty.NAZWA_PRZEDM, typy_przedmiotow.NAZWA_TPRZEDM
from przedmioty
join typy_przedmiotow on przedmioty.KOD_TPRZEDM = typy_przedmiotow.KOD_TPRZEDM
join rozklady on przedmioty.nr_przedm=rozklady.nr_przedm
where nr_sali = 2 OR nr_sali = 4;

select pracownicy.nazwisko from pracownicy
join rozklady on pracownicy.NR_PRAC = rozklady.nr_prac
join sale on sale.nr_sali = rozklady.nr_sali
where rozm_sali>20 and ekran='T'
order by nazwisko;

select przedmioty.nazwa_przedm, pracownicy.NR_ZESP
from rozklady
join przedmioty on przedmioty.NR_przedm = rozklady.NR_przedm
join pracownicy on pracownicy.nr_prac=rozklady.nr_prac
where rozklady.dzien='SRO' AND rozklady.godzina = 10;

select przedm.nazwa_przedm as przed, przedm_nadrz.nazwa_przedm as przed_nadrz
from przedmioty as przedm
join przedmioty as przedm_nadrz on przedm_nadrz.NR_PRZEDM = przedm.NR_PRZEDM_NADRZ;

select count(distinct(nazwa_przedm)) as liczba_roznych_przedm from przedmioty
join pracownicy on przedmioty.nr_odp_prac=pracownicy.nr_prac
where nazwisko = 'JANECZEK';

select min(data_ur) from pracownicy
join przedmioty on przedmioty.nr_odp_prac = pracownicy.nr_prac
where nr_odp_prac=nr_prac;

select max(rozm_sali)as rozmiar_najwiekszej_sali from sale
join rozklady on rozklady.NR_SALI = sale.nr_sali
where godzina between 12 and 14 and dzien = 'PON';

select sale.nr_sali, count(rozklady.nr_przedm) from sale
join rozklady on sale.NR_SALI=rozklady.nr_sali
group by sale.nr_sali;

select przedmioty.NAZWA_PRZEDM, avg(oceny.ocena) from przedmioty
join oceny on oceny.nr_przedm = przedmioty.nr_przedm
group by przedmioty.nazwa_przedm;

select min(data_ur) as data_ur_najstarszego, nazwa_przedm from pracownicy
join rozklady on pracownicy.nr_prac=rozklady.nr_prac
join przedmioty on rozklady.NR_PRZEDM = przedmioty.nr_przedm
group by nazwa_przedm;

select count(nr_prac) as liczba_prowadzacych, nazwa_przedm from rozklady
join przedmioty on rozklady.NR_PRZEDM = przedmioty.nr_przedm
group by nazwa_przedm;

select pracownicy.NAZWISKO from pracownicy
join rozklady on pracownicy.NR_PRAC = rozklady.NR_PRAC
where dzien='PON'
group by nazwisko
having count(nazwisko) > 1;

select przedmioty.nazwa_przedm, count(rozklady.nr_sali) from przedmioty
join rozklady on przedmioty.NR_PRZEDM= rozklady.nr_przedm
group by rozklady.nr_przedm, przedmioty.nazwa_przedm
having count(rozklady.nr_sali)>1;

select przedmioty.nazwa_przedm from przedmioty
join pracownicy on pracownicy.nr_prac = przedmioty.nr_odp_prac
where pracownicy.data_ur > (select pracownicy.data_ur from pracownicy
where nazwisko = 'Janeczek');

select pracownicy.nazwisko from pracownicy
join wyplaty on wyplaty.nr_prac=pracownicy.nr_prac
group by nazwisko
having avg(wyplaty.kwota)<
(select avg(wyplaty.kwota) from wyplaty
join pracownicy on wyplaty.nr_prac=pracownicy.nr_prac
group by nazwisko
having nazwisko = 'Misiura');

select przedmioty.nazwa_przedm from przedmioty
join rozklady on przedmioty.NR_PRZEDM= rozklady.nr_przedm
join pracownicy on pracownicy.NR_PRAC = rozklady.NR_PRAC
group by przedmioty.nazwa_przedm
having count(pracownicy.NR_ZESP)> (select count(pracownicy.nr_zesp) from pracownicy
join rozklady on rozklady.nr_prac = pracownicy.nr_prac
join przedmioty on przedmioty.nr_przedm = rozklady.nr_przedm
group by przedmioty.nazwa_przedm
having przedmioty.nazwa_przedm = 'Algebra');