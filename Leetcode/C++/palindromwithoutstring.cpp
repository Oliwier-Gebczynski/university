class Solution {
public:
    bool isPalindrome(int x) {
        int xcopy = x;

        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        long long int reversed = 0;
        while(true){
            reversed = reversed * 10 + x % 10;
            x = x / 10;

            if (x == 0){
                break;
            }
        }

        if (reversed == xcopy){
            return true;
        }else{
            return false;
        }
    }
};
