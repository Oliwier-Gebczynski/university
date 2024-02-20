class Solution {
public:
    string addBinary(string a, string b) {
        int n = max(a.length(), b.length());
        a = string(n - a.length(), '0') + a;
        b = string(n - b.length(), '0') + b;

        string result;
        int carry = 0;
        for (int i = n - 1; i >= 0; --i) {
            int aDigit = a[i] - '0';
            int bDigit = b[i] - '0';
            int sum = (aDigit + bDigit + carry) % 2;
            carry = (aDigit + bDigit + carry) / 2;
            result = to_string(sum) + result;
        }

        if (carry) {
            result = '1' + result;
        }

        return result;
    }
};
