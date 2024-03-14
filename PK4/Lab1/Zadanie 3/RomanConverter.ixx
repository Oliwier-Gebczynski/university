export module RomanConverter;
export import :CatchZero;
import <iostream>;
import <vector>;


export void convert_to_roman(int number) {
    catchZero(number);

    std::vector<std::pair<int, std::string>> roman_values = {
        {1000, "M"}, {900, "CM"}, {500, "D"}, {400, "CD"}, {100, "C"},
        {90, "XC"}, {50, "L"}, {40, "XL"}, {10, "X"}, {9, "IX"},
        {5, "V"}, {1, "I"}
    };

    int i = 0;
    while (number > 0) {
        int value = roman_values[i].first;
        std::string symbol = roman_values[i].second;

        int quotient = number / value;
        number %= value;

        for (int j = 0; j < quotient; j++) {
            std::cout << symbol;
        }

        i++;
    }

    std::cout << std::endl;
}