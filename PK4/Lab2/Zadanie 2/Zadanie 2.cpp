#include <iostream>
#include <regex>
#include <vector>

bool isEmailValid(const std::string& email) {
    std::regex email_regex(
        R"(^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,3}$)"
    );

    return std::regex_match(email, email_regex);
}

int main() {
    
    std::vector<std::string> emails = {
      "johndoe@example.com",
      "janedoe@gmail.com",
      "test123@xyz.net",
      "invalid_email",
      "user@123.456.789.10",
      "username@subdomain.example.com"
    };

    for (const std::string& email : emails) {
        std::cout << email << ": ";
        if (isEmailValid(email)) {
            std::cout << "Valid email address" << std::endl;
        }
        else {
            std::cout << "Invalid email address" << std::endl;
        }
    }

    return 0;
}
