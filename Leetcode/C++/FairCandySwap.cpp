class Solution {
public:
    std::vector<int> fairCandySwap(const std::vector<int>& aliceSizes, const std::vector<int>& bobSizes) {
        int aliceTotal = 0;
        for (int size : aliceSizes) {
            aliceTotal += size;
        }

        int bobTotal = 0;
        for (int size : bobSizes) {
            bobTotal += size;
        }

        int difference = (bobTotal - aliceTotal) / 2;

        std::map<int, int> bobCandyMap;
        for (int size : bobSizes) {
            bobCandyMap[size]++;
        }

        for (int aliceCandy : aliceSizes) {
            int targetCandy = aliceCandy + difference;
            if (bobCandyMap.count(targetCandy) > 0) {
            return {aliceCandy, targetCandy};
            }
        }

        return {};
    }
};