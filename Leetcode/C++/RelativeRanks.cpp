class Solution {
public:
    vector<string> findRelativeRanks(vector<int>& score) {
        std::vector<int> sorted_scores(score);
        std::sort(sorted_scores.begin(), sorted_scores.end(), std::greater<int>());

        std::unordered_map<int, int> rank_map;
        for (int i = 0; i < sorted_scores.size(); ++i) {
            rank_map[sorted_scores[i]] = i + 1;
        }

        std::vector<std::string> ranks;
        for (int s : score) {
            if (rank_map.count(s)) {
                int rank = rank_map[s];
                if (rank == 1) {
                    ranks.push_back("Gold Medal");
                } else if (rank == 2) {
                    ranks.push_back("Silver Medal");
                } else if (rank == 3) {
                    ranks.push_back("Bronze Medal");
                } else {
                    ranks.push_back(std::to_string(rank));
                }
            } else {
                ranks.push_back("NA");
            }
        }
        return ranks;
    }
};