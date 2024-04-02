#include <filesystem>
#include <fstream>
#include <iostream>

int main() {
    std::filesystem::path path = "output.txt";
    std::ofstream output_file(path);

    if (output_file.is_open()) {
        output_file << "Witaj, świecie!" << std::endl;
        output_file.close();
        std::cout << "File created and written successfully!" << std::endl;
    }
    else {
        std::cerr << "Error opening file: " << path << std::endl;
    }

    return 0;
}
