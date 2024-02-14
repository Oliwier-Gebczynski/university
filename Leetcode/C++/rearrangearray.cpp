#include <vector>

class Solution {
public:
    std::vector<int> rearrangeArray(std::vector<int>& nums) {
        std::vector<int> positive;
        std::vector<int> negative;
        std::vector<int> result;
        for(int elem: nums){
            if (elem > 0){
                positive.push_back(elem);
            }else{
                negative.push_back(elem);
            }
        }
        for (int i = 0; i < positive.size(); i++){
            result.push_back(positive[i]);
            result.push_back(negative[i]);
        }

        return result;
    }
};

int main(){
    Solution g;
    std::vector<int> input = {3,1,-2,-5,2,-4};
    auto result = g.rearrangeArray(input);
    return 0;
}