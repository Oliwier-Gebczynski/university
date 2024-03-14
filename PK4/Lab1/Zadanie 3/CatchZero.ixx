export module RomanConverter:CatchZero;
import <iostream>;

export void catchZero(int num) {
	if (num == 0) {
		std::cout << "Nie mozna zamienic zera!" << std::endl;
	}
};