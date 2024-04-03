class Solution {
public:
    bool isIsomorphic(const std::string& s, const std::string& t) {
        if (s.length() != t.length()) {
            return false;
        }

        std::unordered_map<char, char> charToCharS;
        std::unordered_map<char, char> charToCharT;

        for (size_t i = 0; i < s.length(); ++i) {
            char c1 = s[i];
            char c2 = t[i];

            if (charToCharS.count(c1) == 0) {
            if (charToCharT.count(c2) > 0) {
                return false;
            }
            charToCharS[c1] = c2;
            } else {
            if (charToCharS[c1] != c2) {
                return false;
            }
            }

            if (charToCharT.count(c2) == 0) {
            charToCharT[c2] = c1;
            } else {
            if (charToCharT[c2] != c1) {
                return false;
            }
            }
        }

        return true;
    }
};