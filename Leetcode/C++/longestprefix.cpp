class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        int shortest = INT_MAX;
        string result;

        if (strs.empty()){
            return "";
        }

        for (const auto& word : strs) {
            if (shortest > word.length()) {
                shortest = word.length();
            }
        }

        for (int i = 0; i < shortest; i++) {
            char currentChar = strs[0][i];

            for (const auto& word : strs) {
                if (word[i] != currentChar) {
                    return result;
                }
            }
            result += currentChar;
        }
        return result;
    }
};