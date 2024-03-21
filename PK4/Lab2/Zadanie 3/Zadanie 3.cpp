#include <iostream>
#include <fstream>
#include <regex>
#include <set>

std::set<std::string> findUniqueWords(const std::string& filename, const std::regex& word_regex) {
    std::ifstream file(filename);
    std::string line;
    std::set<std::string> unique_words;

    if (file.is_open()) {
        while (std::getline(file, line)) {
            std::sregex_iterator words_begin(line.begin(), line.end(), word_regex);
            std::sregex_iterator words_end;
            for (std::sregex_iterator i = words_begin; i != words_end; ++i) {
                unique_words.insert(i->str());
            }
        }
    }
    else {
        std::cerr << "Error opening file: " << filename << std::endl;
    }

    return unique_words;
}

int main() {
    std::regex word_regex(R"(\b[a][A-Za-z]{3,}[mtbr]\b)");

    std::set<std::string> unique_words = findUniqueWords("file.txt", word_regex);
    
    int counter = 0;
    std::cout << "Unique words: " << std::endl;

    for (const auto& word : unique_words) {
        std::cout << word << std::endl;
        counter += 1;
    }

    std::cout << "Amount: " << counter << std::endl;

    return 0;
}
