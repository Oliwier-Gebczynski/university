class Solution {
public:
    bool arrayStringsAreEqual(vector<string>& word1, vector<string>& word2) {
        std::string word_first, word_second;

        for(auto elem: word1){
            word_first += elem;
        }

        for(auto elem: word2){
            word_second += elem;
        }

        if (word_first == word_second){
            return true;
        }

        return false;
    }
};