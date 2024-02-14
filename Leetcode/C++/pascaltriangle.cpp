#include <vector>

class Solution {
public:
    std::vector<std::vector<int>> generate(int numRows) {
        if (numRows == 1){
            return std::vector<std::vector<int>> {{1}};
        }
        std::vector<std::vector<int>> result = {{1}, {1, 1}};
        for (int i = 2; i < numRows; i++) {
            std::vector<int> new_row(i + 1, 1);
            for (int j = 1; j < i; j++) {
                new_row[j] = result[i - 1][j - 1] + result[i - 1][j];
            }
            result.push_back(new_row);
        }
        return result;
    }
};

int main(){
    Solution g;
    g.generate(3);
    return 0;
}
