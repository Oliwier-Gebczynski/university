#include <filesystem>
#include <fstream>
#include <iostream>

int main() {
	std::filesystem::path source_path = "data.txt";
	std::filesystem::path destination_path = "copy.txt";

	std::ifstream source_file(source_path);
	std::ofstream destination_file(destination_path);

	destination_file << source_file.rdbuf();
	source_file.close();
	destination_file.close();

	std::cout << "Done!";

	return 0;
}
