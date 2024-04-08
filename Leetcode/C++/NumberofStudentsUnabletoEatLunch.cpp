class Solution {
public:
    int countStudents(std::vector<int>& students, std::vector<int>& sandwiches) {
        int zeroes = std::count(students.begin(), students.end(), 0);
        int ones = std::count(students.begin(), students.end(), 1);
        int Size = sandwiches.size();

        for(int i = 0; i < Size; i++){
            if((sandwiches[i] == 0)&&(zeroes > 0)){
                zeroes--;
            }else if((sandwiches[i] == 1)&&(ones > 0)){
                ones--;
            }else{
                return Size - i;
            }
        }

        return 0;
    }
};