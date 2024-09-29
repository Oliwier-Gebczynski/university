// my solution 
class Solution {
public:
    int findCenter(vector<vector<int>>& edges) {
        std::map<int, int> elemCounter;
        for(auto edge: edges){
            if(elemCounter.find(edge[0]) != elemCounter.end()){
                elemCounter[edge[0]]++;
            }else{
                elemCounter[edge[0]] = 1;
            }

            if(elemCounter.find(edge[1]) != elemCounter.end()){
                elemCounter[edge[1]]++;
            }else{
                elemCounter[edge[1]] = 1;
            }
        }

        int mostFreqElem = 0;
        int maxCount = 0;
        for(auto element: elemCounter){
            if (element.second > maxCount) {
                maxCount = element.second;
                mostFreqElem = element.first;
            }
        }

        return mostFreqElem;
    }
};

// better solution 
class Solution {
public:
    int findCenter(vector<vector<int>>& edges) {
        // Compare the first two edges to find the common node
        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        } else {
            return edges[0][1];
        }
    }
};