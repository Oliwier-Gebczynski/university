#include <vector>
#include <queue>
#include <limits>

class Solution {
public:
    int furthestBuilding(vector<int>& heights, int bricks, int ladders) {
        std::priority_queue<int> heap;
        int index = 0;

        for (int i = 0; i < static_cast<int>(heights.size()) - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff <= 0) {
                index++;
                continue;
            }

            heap.push(diff);
            bricks -= diff;

            if (bricks < 0) {
                if (ladders > 0) {
                    if (!heap.empty() && bricks + heap.top() >= 0) {
                        bricks += heap.top();
                        heap.pop();
                        ladders--;
                        index++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                index++;
            }
        }

        return index;
    }
};

int main(){
    Solution g;

    std::vector<int> arr = {2,7,9,12};
    int ladders = 1;
    int bricks = 5;

    g.furthestBuilding(arr, bricks, ladders);
}
