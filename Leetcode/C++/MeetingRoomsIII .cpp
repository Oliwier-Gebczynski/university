class Solution {
public:
    int mostBooked(int n, std::vector<std::vector<int>>& meetings) {
        sort(meetings.begin(), meetings.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] < b[0];
        });

        priority_queue<vector<long long>, vector<vector<long long>>, greater<vector<long long>>> occupiedResources;
        priority_queue<int, vector<int>, greater<int>> availableResources;
        for (int i = 0; i < n; ++i) {
            availableResources.push(i);
        }

        vector<long long> resourceCounts(n, 0);
        for (const vector<int>& meeting : meetings) {
            long long startTime = meeting[0], endTime = meeting[1];
            while (!occupiedResources.empty() && occupiedResources.top()[0] <= startTime) {
                availableResources.push(occupiedResources.top()[1]);
                occupiedResources.pop();
            }

            int assignedResource;
            if (!availableResources.empty()) {
                assignedResource = availableResources.top();
                availableResources.pop();
                occupiedResources.push({endTime, assignedResource});
            } else {
                vector<long long> earliestEnd = occupiedResources.top();
                occupiedResources.pop();
                assignedResource = earliestEnd[1];
                long long delta = startTime - earliestEnd[0];
                if (endTime - delta >= 0) {
                    occupiedResources.push({endTime - delta, assignedResource});
                } else {
                    cout << "Error: Integer overflow occurred!" << endl;
                    return -1;
                }
            }

            resourceCounts[assignedResource]++;
        }

        int mostBookedResource = 0;
        for (int i = 0; i < n; ++i) {
            if (resourceCounts[mostBookedResource] < resourceCounts[i]) {
                mostBookedResource = i;
            }
        }

        return mostBookedResource;
    }
};

int main(){
    Solution g;
    int rooms = 2;
    std::vector<std::vector<int>> arr = {{43,44},{34,36},{11,47},{1,8},{30,33},{45,48},{23,41},{29,30}};
    auto result = g.mostBooked(rooms, arr);

    std::cout << result;
}

