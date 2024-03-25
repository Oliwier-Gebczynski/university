class Solution {
public:
    char findTheDifference(std::string s, std::string t) {
        int val = 0;
        for(auto c : s) {
            val ^= c;
        }        
        for (auto c: t) {
            val ^= c;
        }

        return (char) val;
    }
};