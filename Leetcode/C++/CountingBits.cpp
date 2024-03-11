class Solution {
public:
    int bitCalc(int z) {
        int count = 0;
        while (z) {
            count += (z & 1);
            z >>= 1;          
        }
        return count;
    }

    vector<int> countBits(int n) {
        std::vector<int> result;
        
        for(int i = 0; i <= n; i++){
            result.push_back(bitCalc(i));
        }
        
        return result;
    }
};