class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int counter = count(nums.begin(), nums.end(), 0);

        auto it = std::remove(nums.begin(), nums.end(), 0);
        nums.erase(it, nums.end());

        for(int i = 0; i < counter; i++){
            nums.push_back(0);
        }
    }
};