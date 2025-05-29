class Solution {
    double px[32];
public:
    double myPow(double x, int n) { 
        if(n<0) 
        {
            n = -n;
            x = 1.0/x;
        }
        px[0] = x;
        double result = 1.0;
        if ( ((n>>0) & 0x1) == 1)
            result *= px[0];
        for(int i =1; i < 32; i ++)
        {
            if( (n>>i) == 0)
                break;
            px[i] = px[i-1]*px[i-1];
            if ( ((n >>i) & 0x1) == 1)
                result *= px[i];
        }
        return result;
        
    }
};