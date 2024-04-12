#include <iostream>
#include <vector>
#include <ranges>
#include <algorithm>
#include <random>
#include <string>

int main()
{
    std::vector<int> data;

    for (int i = 0; i < 10; i++) {
        data.push_back(rand() % 100 + 1);
    }

    std::cout << "Poczatkowe dane: ";

    for (auto element : data) {
        std::cout << element << ", ";
    }

    std::cout << std::endl;

    auto view = data | std::views::filter([](auto const n) { return n % 2 == 0; }) | std::views::transform([](auto const n) { return n * n; });

    std::cout << "Dane po zmianie: ";
    
    for (auto element : view) {
        std::cout << element << ", ";
    }

}

