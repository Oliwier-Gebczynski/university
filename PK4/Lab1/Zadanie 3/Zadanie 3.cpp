import RomanConverter;
import <iostream>;

int main() {
	int number;
	std::cout << "Podaj liczbe: ";
	std::cin >> number;

	std::cout << "Zamieniona liczba ( " << number << "): ";
	convert_to_roman(number);
}