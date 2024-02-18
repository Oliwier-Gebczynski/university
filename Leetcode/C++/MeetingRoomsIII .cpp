#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#include <iostream>

struct Room {
    bool available;
    int numberMeetings;
    int totalDuration;
};

bool compareFirstElement(const std::vector<int>& a, const std::vector<int>& b) {
    return a[0] < b[0];
}

class Solution {
public:
    int mostBooked(int n, std::vector<std::vector<int>>& meetings) {
        std::sort(meetings.begin(), meetings.end(), compareFirstElement);
        std::reverse(meetings.begin(), meetings.end());

        std::map<int, Room> rooms;
        for (int i = 0; i < n; ++i) {
            rooms[i] = {true, 0, 0};
        }

        std::priority_queue<int, std::vector<int>, std::greater<int>> availableRooms;
        for (int i = 0; i < n; ++i) {
            availableRooms.push(i);
        }

        int iteration = 0;
        while(!meetings.empty()){
            for (auto& room : rooms) {
                if (!room.second.available && room.second.totalDuration > 0) {
                    room.second.totalDuration--;
                    if (room.second.totalDuration <= 0) {
                        room.second.available = true;
                        availableRooms.push(room.first);
                    }
                }
            }

            if(!availableRooms.empty()){
                if(meetings.back()[0] < iteration){
                    int current = availableRooms.top();
                    availableRooms.pop();

                    rooms[current].available = false;
                    rooms[current].numberMeetings++;
                    rooms[current].totalDuration += meetings.back()[1]- meetings.back()[0];

                    meetings.pop_back();
                }
            }

            iteration++;
        }

        int biggestValue = 0;
        int biggestId;
        for (auto& room : rooms) {
            std::cout << "Id: " << room.first << ", Value: " << room.second.numberMeetings << std::endl;
            if (room.second.numberMeetings > biggestValue) {
                biggestValue = room.second.numberMeetings;
                biggestId = room.first;
            }
        }

        return biggestId;
    }
};

int main(){
    Solution g;
    int rooms = 2;
    std::vector<std::vector<int>> arr = {{43,44},{34,36},{11,47},{1,8},{30,33},{45,48},{23,41},{29,30}};
    auto result = g.mostBooked(rooms, arr);

    std::cout << result;
}

