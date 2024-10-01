class Solution {
public:
    bool checkOnesSegment(string s) {
        bool foundZero = false;
        
        for (auto c : s) {
            if (c == '0') {
                foundZero = true;
            } else if (foundZero && c == '1') {
                return false;
            }
        }
        return true;
    }
};