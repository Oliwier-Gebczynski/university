#include <iostream>
#include <fstream>
#include <regex>

void zliczWystapienia(const std::string& plik, int& soplica_count, int& zosia_count, std::string& zosia_line) {
    std::ifstream file(plik);
    std::string line;
    std::regex soplica_regex(R"(Soplica\b)");
    std::regex zosia_regex(R"(\bZosia\b)");

    soplica_count = 0;
    zosia_count = 0;
    zosia_line.clear();

    while (std::getline(file, line)) {
        if (std::regex_search(line, soplica_regex)) {
            soplica_count++;
        }

        std::sregex_iterator zosia_begin(line.begin(), line.end(), zosia_regex);
        std::sregex_iterator zosia_end;
        for (std::sregex_iterator i = zosia_begin; i != zosia_end; ++i) {
            zosia_count++;
            if (zosia_count == 23) {
                zosia_line = line;
                break;
            }
        }
    }
}

int main() {
    int soplica_count = 0;
    int zosia_count = 0;
    std::string zosia_line;

    zliczWystapienia("file.txt", soplica_count, zosia_count, zosia_line);

    std::cout << "Liczba wystapien slowa 'Soplica': " << soplica_count << std::endl;
    if (!zosia_line.empty()) {
        std::cout << "Linia z 23-cim wystapieniem slowa 'Zosia': " << zosia_line << std::endl;
    }

    return 0;
}