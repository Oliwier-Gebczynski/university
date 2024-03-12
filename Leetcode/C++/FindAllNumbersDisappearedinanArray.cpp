class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int n = nums.size();
        std::set<int> expectedNumbers;
        for (int i = 1; i <= n; ++i) {
            expectedNumbers.insert(i);
        }

        for (int num : nums) {
            if (expectedNumbers.count(num) > 0) {
            expectedNumbers.erase(num);
            }
        }

        std::vector<int> result(expectedNumbers.begin(), expectedNumbers.end());
        return result;
    }
};