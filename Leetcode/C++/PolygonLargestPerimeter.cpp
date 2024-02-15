class Solution {
public:
    long long largestPerimeter(vector<int>& nums) {
        sort(nums.begin(),nums.end());

        for (int i = 0; i < nums.size(); i++) {
            total += nums[i];
        }

        for(long long i = nums.size() - 1; i > 0; i--){
            if((test - nums[i] <= nums[i]) && ((test - nums[i] > 0))){
                test -= nums[i];
                nums.pop_back();
            }
        }

        if (nums.size() > 2){
            return test;
        }else{
            return -1;
        }
    }
};