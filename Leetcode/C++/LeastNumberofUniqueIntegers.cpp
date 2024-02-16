class Solution {
public:
    int findLeastNumOfUniqueInts(vector<int>& inputData, int elementsToRemove) {
        unordered_map<int, int> frequencyMap;
        for (auto& number : inputData) {
            frequencyMap[number]++;
        }

        vector<pair<int, int>> listOfPairs;
        for (auto& frequencyNumberPair : frequencyMap) {
            listOfPairs.push_back({frequencyNumberPair.second, frequencyNumberPair.first});
        }

        sort(listOfPairs.rbegin(), listOfPairs.rend());

        while (elementsToRemove > 0) {
            int requiredRemoval = min(elementsToRemove, listOfPairs.back().first);
            elementsToRemove -= requiredRemoval;
            listOfPairs.back().first -= requiredRemoval;

            if (!listOfPairs.back().first) {
                listOfPairs.pop_back();
            }
        }

        return listOfPairs.size();
    }

};