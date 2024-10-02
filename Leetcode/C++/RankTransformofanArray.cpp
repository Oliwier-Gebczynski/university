class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        std::vector<int> sortedArr = arr;
        std::sort(sortedArr.begin(), sortedArr.end());
        
        sortedArr.erase(std::unique(sortedArr.begin(), sortedArr.end()), sortedArr.end());

        unordered_map<int, int> rankMap;
        
        for (int i = 0; i < sortedArr.size(); ++i) {
            rankMap[sortedArr[i]] = i + 1;
        }
        
        for (int i = 0; i < arr.size(); ++i) {
            arr[i] = rankMap[arr[i]];
        }
        
        return arr;
    }
};