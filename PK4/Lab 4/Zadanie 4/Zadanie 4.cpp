#include <vector>
#include <iostream>
#include <ranges>
#include <algorithm>
#include <random>
#include <string>

int main()
{
    std::vector<std::pair<std::string, int>> persons = { {"Janusz", 70},{"Kacper", 18}, {"Oskar", 45 }, {"Alicja", 60}, {"Zuzanna", 29}};
	auto under50 = persons | std::views::filter([](auto const n) { return n.second < 50; });

	std::cout << "Osoby ponizej mlodsze niz 50lat: ";

	for (auto person : under50) {
		std::cout << person.first << " | ";
	}

	return 0;
}

