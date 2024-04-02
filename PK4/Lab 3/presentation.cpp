#include <iostream>
#include <filesystem>
#include <fstream>

int main() {
    // Ścieżka do pliku
    std::filesystem::path directoryPath("/data");

    // Jeżeli ściezka nie istnieje to ją tworzymy, sprawdzanie bledow
    try {
        if (!std::filesystem::exists(directoryPath)) {
            std::filesystem::create_directory(directoryPath);
            std::cout << "Sciezka zostala stworzona." << std::endl;
        }
    }
    catch (const std::filesystem::filesystem_error& ex) {
        std::cerr << "Blad tworzenia sciezki: " << ex.what() << std::endl;
        return 1;
    }

    //Sciezka do pliku, w powyzszej sciezce
    std::filesystem::path filePath = directoryPath / "text.txt";

    //Sprawdzamy czy plik istnieje i jesli nie to tworzymy plik, zapełniając go losowymi wartościami
    if (!std::filesystem::exists(filePath)){
        std::ofstream file(filePath);
        if (file.is_open()) {
            file << "Przykladowe dane.";
            file.close();
            std::cout << "Plik stworzony i dane zapisane." << std::endl;
        }
        else {
            std::cerr << "Blad otwierania pliku do zapisu." << std::endl;
            return 1;
        }
    }
    else {
        std::cout << "Plik juz istnieje." << std::endl;
    }


    // Wyświetlenie informacji o ścieżce pliku
    std::cout << "Sciezka do pliku: " << filePath << std::endl;

    // Sprawdzenie istnienia pliku
    if (std::filesystem::exists(filePath)) {
        std::cout << "Plik istnieje." << std::endl;
    }
    else {
        std::cerr << "Plik nie istnieje." << std::endl;
        return 1;
    }

    // Pobranie statusu i rodzaju pliku pliku
    std::filesystem::file_status fileStatus = std::filesystem::status(filePath);
    std::cout << "File type: ";
    if (std::filesystem::is_regular_file(fileStatus)) {
        std::cout << "Plik regularny" << std::endl;
    }
    else if (std::filesystem::is_directory(fileStatus)) {
        std::cout << "Sciezka" << std::endl;
    }
    else {
        std::cout << "Inne" << std::endl;
    }


    
   // Wyswietlanie uprawnien plikow (w konwencji linuxowej owner,group,others)
    std::filesystem::perms filePerms = std::filesystem::status(filePath).permissions(); 
    std::cout << "Uprawnienia pliku: ";
    // Właściciel
    std::cout << ((filePerms & std::filesystem::perms::owner_read) != std::filesystem::perms::none ? "r" : "-");
    std::cout << ((filePerms & std::filesystem::perms::owner_write) != std::filesystem::perms::none ? "w" : "-");
    std::cout << ((filePerms & std::filesystem::perms::owner_exec) != std::filesystem::perms::none ? "x " : "- ");

    // Grupa
    std::cout << ((filePerms & std::filesystem::perms::group_read) != std::filesystem::perms::none ? "r" : "-");
    std::cout << ((filePerms & std::filesystem::perms::group_write) != std::filesystem::perms::none ? "w" : "-");
    std::cout << ((filePerms & std::filesystem::perms::group_exec) != std::filesystem::perms::none ? "x " : "- ");

    // Inni
    std::cout << ((filePerms & std::filesystem::perms::others_read) != std::filesystem::perms::none ? "r" : "-");
    std::cout << ((filePerms & std::filesystem::perms::others_write) != std::filesystem::perms::none ? "w" : "-");
    std::cout << ((filePerms & std::filesystem::perms::others_exec) != std::filesystem::perms::none ? "x " : "- ");

    std::cout << std::endl;

    // Kopiowanie pliku z opcjami kopiowania
    std::filesystem::path source_path = filePath;
    std::filesystem::path destination_path = directoryPath / "copy.txt";

    // Tworzenie pliku docelowego, jeśli nie istnieje
    std::ofstream outFile(destination_path);
    if (!outFile.is_open()) {
        std::cerr << "Błąd otwierania pliku do zapisu." << std::endl;
        return 1;
    }
    outFile.close();

    // Sprawdzenie, czy plik docelowy został utworzony pomyślnie
    if (!std::filesystem::exists(destination_path)) {
        std::cerr << "Błąd tworzenia pliku docelowego." << std::endl;
        return 1;
    }

    // Kopiowanie pliku źródłowego do pliku docelowego
    try {
        std::filesystem::copy_file(source_path, destination_path, std::filesystem::copy_options::overwrite_existing);
        std::cout << "Plik skopiowany pomyślnie." << std::endl;
    }
    catch (const std::filesystem::filesystem_error& ex) {
        std::cerr << "Błąd kopiowania pliku: " << ex.what() << std::endl;
        return 1;
    }



    // Iterowanie po zawartości katalogu (opcjonalnie)
    for (auto it : std::filesystem::directory_iterator(directoryPath)) {
        std::cout << it.path() << std::endl;
    }

    // Pobranie czasu modyfikacji pliku
    std::filesystem::file_time_type lastWriteTime = std::filesystem::last_write_time(filePath);
    std::cout << "Czas modyfikacji pliku: " << lastWriteTime << std::endl;

    // Pobranie informacji o zużyciu miejsca na dysku przez plik
    std::filesystem::space_info spaceInfo = std::filesystem::space(filePath);
    std::cout << "Rozmiar pliku: " << spaceInfo.capacity << " bajtow" << std::endl;


    return 0;
}