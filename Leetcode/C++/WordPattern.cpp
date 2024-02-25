class Solution {
public:
    bool wordPattern(string pattern, string s) {
        vector<string> words;
        istringstream iss(s);
        string word;
        while (getline(iss, word, ' ')) {
            words.push_back(word);
        }

        if (words.size() != pattern.length()) {
            return false;
        }

        unordered_map<char, string> charMap;
        unordered_map<string, char> wordMap;

        for (int i = 0; i < pattern.length(); ++i) {
            char ch = pattern[i];
            string word = words[i];

            if (charMap.count(ch) == 0) {
                if (wordMap.count(word) > 0) {
                    return false;
                }
                charMap[ch] = word;
                wordMap[word] = ch;
            } else {
                if (charMap[ch] != word) {
                    return false;
                }
            }
        }

        return true;
    }
};