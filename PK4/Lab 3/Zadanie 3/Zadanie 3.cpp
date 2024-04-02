#include <filesystem>
#include <fstream>
#include <iostream>

int main() {
	std::filesystem::path source_path = "data.txt";
	std::filesystem::path destination_path = "copy.txt";

	std::filesystem::copy_file(source_path, destination_path, std::filesystem::copy_options::overwrite_existing);

	std::cout << "Done!";

	return 0;
}
