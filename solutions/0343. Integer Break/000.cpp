class Solution {
public:
    
    int integerBreak(int n) {
        int a[4] = {1,2,4,6};
        if( (n>=2) && (n <= 5))    
            return a[n-2];
        int b[4] = {2,3,4,6};
        int result = 1;
        if(n%6==1)
        {
            result = result*12;
            n = n - 7;
        }
        int x = n / 6;
        result = result * pow(9,x);
        int y = n % 6;
        if(y!=0)
            result = result * b[y-2];
        return result;
        
        
        
    }
};