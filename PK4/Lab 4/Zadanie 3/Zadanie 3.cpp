#include <iostream>
#include <vector>
#include <ranges>
#include <algorithm>
#include <random>
#include <string>


int main()
{
    std::vector<int> data = { 1, 2, 3, 4, 5, 6 };
	std::vector<float> result;

	auto square = data | std::views::transform([](float const n) {return n * n / 2; });
	auto circle = data | std::views::transform([](float const n) {return n * n * 3, 14; });
	auto triangle = data | std::views::transform([](float const n) {return n * n * sqrt(3) / 4; });
	auto diamond = data | std::views::transform([](float const n) {return (n * 2 * n) / 2;  });     //pole rombu o przekatnych n i 2n

	result.insert(result.end(), std::begin(square), std::end(square));
	result.insert(result.end(), std::begin(circle), std::end(circle));
	result.insert(result.end(), std::begin(triangle), std::end(triangle));
	result.insert(result.end(), std::begin(diamond), std::end(diamond));

	const auto duplicates = std::ranges::unique(result);
	result.erase(std::ranges::begin(duplicates), std::ranges::end(duplicates));

	std::random_device rd;
	std::mt19937 gen{ rd() };
	std::ranges::shuffle(result, gen);

	std::cout << "Wynik zadania: ";

	for (auto element : result) {
		std::cout << element << " | ";
	}

	return 0;
}

