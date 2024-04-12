#include <iostream>
#include <vector>
#include <ranges>
#include <algorithm>
#include <random>
#include <string>

int main()
{
    std::vector<int> data = { 1,2,3,4,5,6 };
    
    std::cout << "Przed zmiana: ";

    for (auto element : data) {
        std::cout << element << ", ";
    }

    std::cout << std::endl;

    
    auto view = data | std::views::take(3) | std::views::reverse;
    std::ranges::sort(view);

    std::cout << "Po zmianie: ";

    for (auto element : data) {
        std::cout << element << ", ";
    }

    return 0;
}