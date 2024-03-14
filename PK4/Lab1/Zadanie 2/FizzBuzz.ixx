export module FizzBuzz;

import <iostream>;
import <string>;
import<vector>;

export void FizzBuzz(std::vector<int> v) {
    std::vector<std::string> result;
    for (int i = 0; i < v.size(); i++) {
        std::string element;
        if (v[i] % 3 == 0) {
            element = "Fizz";
        }
        if (v[i] % 5 == 0) {
            element += "Buzz";
        }
        if (element.empty()) {
            element = std::to_string(v[i]);
        }
        result.push_back(element);
    }
    for (std::string el : result) {
        std::cout << el << std::endl;
    }
}