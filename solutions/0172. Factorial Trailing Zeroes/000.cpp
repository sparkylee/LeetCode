class Solution {
public:
    int trailingZeroes(int n) {
        if(n < 5)
            return 0;
        else
        {
            int m = n/5;
            return m + trailingZeroes(m) ;
        }
    }
};