class Solution {
public:
    bool isPalindrome(int x) {
        int y = 0;
        int x1 = x;
        while(x1 > 0)
        {
            y = y*10 + x1 % 10;
            x1 = x1 / 10;
        }
        return (y==x);
    }
};