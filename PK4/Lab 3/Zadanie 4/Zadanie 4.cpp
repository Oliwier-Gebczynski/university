#include <filesystem>
#include <iostream>

void list_directory(const std::filesystem::path& path) {
    for (const auto& entry : std::filesystem::directory_iterator(path)) {
        if (std::filesystem::is_directory(entry)) {
            std::cout << "Katalog: " << entry.path() << std::endl;
            list_directory(entry.path());
        }
        else {
            std::cout << "Plik: " << entry.path() << std::endl;
        }
    }
}

int main() {
    std::filesystem::path directory_path = "./";
    list_directory(directory_path);
    return 0;
}