#include <iostream>
#include <string>

class Solution {
public:
    bool isValid(string s) {
        vector<int> stack;

        for (char c : s) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push_back(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char openBracket = stack.back();
                stack.pop_back();
                if ((c == ')' && openBracket != '(') || (c == '}' && openBracket != '{') || (c == ']' && openBracket != '[')) {
                    return false;
                }
            }
        }

        return stack.empty();
    }
};
