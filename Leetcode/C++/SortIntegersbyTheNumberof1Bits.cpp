class Solution {
public:
  vector<int> sortByBits(vector<int>& arr) {
    std::sort(arr.begin(), arr.end(), [](int a, int b) {
        int countA = std::bitset<sizeof(int) * 8>(a).count();
        int countB = std::bitset<sizeof(int) * 8>(b).count();
        return (countA < countB) || (countA == countB && a < b);
    });

    return arr;
  }
};