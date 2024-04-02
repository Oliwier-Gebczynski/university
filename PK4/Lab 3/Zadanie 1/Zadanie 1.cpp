#include <iostream>
#include <filesystem>

int main() {
    std::filesystem::path path = "data.txt";
    if (std::filesystem::exists(path)) {
        std::cout << "Plik istnieje!" << std::endl;
    }
    else {
        std::cout << "Plik nie istnieje." << std::endl;
    }
    return 0;
}
