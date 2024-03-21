#include <iostream>
#include <regex>

bool isPasswordValid(const std::string& password) {
    if (password.size() < 6) {
        return false;
    }

    std::regex upper_case("[A-Z]");
    std::regex lower_case("[a-z]");
    std::regex digit("[0-9]");
    std::regex special_char("[^a-zA-Z0-9]");

    if (std::regex_search(password, upper_case) &&
        std::regex_search(password, lower_case) &&
        std::regex_search(password, digit) &&
        std::regex_search(password, special_char)) {
        return true;
    }
    
    return false;
}

int main() {
    std::cout << "isPasswordValid test:\n";

    std::string passwords[] = {
        "short",           // Too short 
        "ThisIsAValidPwd!", // Invalid (missing digits)
        "OnlyLowercase",   // Invalid (missing uppercase)
        "ALLCAPS",          // Invalid (missing lowercase)
        "NoDigits!",        // Invalid (missing digits)
        "123456",          // Invalid (missing letters and special characters)
        "P@ssw0rd!",       // Valid password
        "Pa$$w0rd",        // Invalid (missing special character)
    };

    for (const std::string& password : passwords) {
        std::cout << password << ": " << (isPasswordValid(password) ? "valid" : "invalid") << std::endl;
    }

    return 0;
}
