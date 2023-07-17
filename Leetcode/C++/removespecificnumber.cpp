class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        vector<int> result;
        int i;

        for (auto element: nums){
            if (element != val){
                result.push_back(element);
                i++;
            }
        }

        nums = result;

        return i;
    }
};