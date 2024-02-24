class Solution {
public:
    int missingNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size() - 1; i++){
            if((nums[i+1] - nums[i]) != 1){
                return nums[i] + 1;
            }
        }

        if(nums.front() == 0){
            return nums.back() + 1;
        }else{
            return 0;
        }
    }
};