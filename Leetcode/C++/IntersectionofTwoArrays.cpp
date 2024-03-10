class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        std::set<int> nums1_set(nums1.begin(), nums1.end());

        std::vector<int> result;
        for (const auto& elem : nums2) {
            if (nums1_set.count(elem) > 0) {
                result.push_back(elem);
            }
        }
        sort(result.begin(), result.end());
        result.erase(std::unique(result.begin(), result.end()), result.end());
        return result;
    }
};