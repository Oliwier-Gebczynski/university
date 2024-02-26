class Solution {
public:
    vector<string> fizzBuzz(int n) {
        std::vector<std::string> result;

        for (int i = 1; i <= n; ++i) {
            std::string output;

            if (i % 3 == 0) {
                output += "Fizz";
            }
            if (i % 5 == 0) {
                output += "Buzz";
            }
            if (output.empty()) {
                std::stringstream ss;
                ss << i;
                output = ss.str();
            }

            result.push_back(output);
        }

        return result;
    }
};