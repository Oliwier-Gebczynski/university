#include <stdio.h>

class Solution {
public:
    int maxDepth(string s) {
        int depth = 0;
        int maxDepth = 0;

        for(auto elem: s){
            if(std::string(1, elem).compare("(") == 0){
                depth += 1;
                std::cout << " ( ";
            }else if(std::string(1, elem).compare(")") == 0){
                depth -= 1;
                std::cout << " ) ";
            }
            if(depth > maxDepth){
                maxDepth = depth;
            }
        }

        return maxDepth;
    }
};