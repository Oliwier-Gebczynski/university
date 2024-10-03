class Solution {
public:
    int minSubarray(vector<int>& nums, int p) {
        long long total_sum = std::accumulate(nums.begin(), nums.end(), 0LL); 
        int remainder = total_sum % p;

        if (remainder == 0) return 0;

        std::unordered_map<int, int> prefix_mod;
        prefix_mod[0] = -1; 
        long long current_sum = 0; 
        int min_len = nums.size(); 

        for (int i = 0; i < nums.size(); ++i) {
            current_sum = (current_sum + nums[i]) % p; 
            current_sum = (current_sum + p) % p;

            int target = (current_sum - remainder + p) % p;

            if (prefix_mod.find(target) != prefix_mod.end()) {
                min_len = std::min(min_len, i - prefix_mod[target]);
            }

            prefix_mod[current_sum] = i;
        }

        if(min_len == nums.size()){
            return -1;
        }else{
            return min_len;
        }
    }
};