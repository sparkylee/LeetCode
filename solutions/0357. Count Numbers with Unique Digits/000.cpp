class Solution {
public:
    int countNumbersWithUniqueDigits(int n) {
        if(n==0)
            return 1;
        if(n==1)
            return 10;
        if(n>10)
            return countNumbersWithUniqueDigits(10);
        int tmp1 = countNumbersWithUniqueDigits(n-1);
        int tmp2 = 1;
        for(int i = 1; i<=n-1;i++)
            tmp2 = tmp2 * (10-i);
        return tmp1 + 9*tmp2;
    }
};