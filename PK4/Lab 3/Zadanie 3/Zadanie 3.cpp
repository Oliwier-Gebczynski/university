#include <iostream>
#include <filesystem>

int main() {
  
  std::filesystem::path plik_zrodlowy("data.txt");

  if (!std::filesystem::is_readable(plik_zrodlowy)) {
    std::cerr << "Brak dostępu do pliku źródłowego: " << plik_zrodlowy << std::endl;
    return 1;
  }


  std::filesystem::path plik_docelowy("copy.txt");

  if (std::filesystem::exists(plik_docelowy)) {
    // Zapytanie o potwierdzenie nadpisania
    std::cout << "Plik docelowy " << plik_docelowy << " już istnieje. Nadpisać? (t/n) ";
    char znak;
    std::cin >> znak;
    if (std::tolower(znak) != 't') {
      std::cout << "Operacja anulowana." << std::endl;
      return 0;
    }
  }

  try {
    std::filesystem::copy_file(plik_zrodlowy, plik_docelowy);
    std::cout << "Plik " << plik_zrodlowy << " został skopiowany do " << plik_docelowy << std::endl;
  } catch (const std::filesystem::filesystem_error& e) {
    std::cerr << "Błąd podczas kopiowania pliku: " << e.what() << std::endl;
    return 1;
  }

  return 0;
}
