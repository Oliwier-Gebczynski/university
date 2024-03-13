class Solution {
public:
    int pivotInteger(int n) {
        int totalSum = 0;
        int leftSum = 0;
        std::vector<int> helpVec;

        for(int i = 0; i < n; i++){
            helpVec.push_back(i+1);
        }

        for (int i = 0; i < n; i++) {
            totalSum += helpVec[i];
        }

        for (int i = 0; i < n; i++) {
            int rightSum = totalSum - leftSum - helpVec[i];
            if (leftSum == rightSum) {
                return i+1;
            }
            leftSum += helpVec[i];
        }

        return -1;
        
    }
};