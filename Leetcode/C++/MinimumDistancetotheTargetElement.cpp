class Solution {
public:
    int getMinDistance(vector<int>& nums, int target, int start) {
        std::vector<int> results;
        for(int i = 0; i < nums.size(); i++){
            if(nums[i] == target){
                results.push_back(abs(i - start));
            }
        }
        std::sort(results.begin(), results.end());
        return results[0];
    }
};