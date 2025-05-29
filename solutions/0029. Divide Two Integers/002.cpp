class Solution {
    
    int checkSign(int  dividend, int divisor)
    {
        if( (dividend > 0 && divisor < 0 ) || (dividend < 0 && divisor > 0))
            return -1;
        return 1;
    }
    unsigned int i2unsigned(int value)
    {
        if(value <0)
            return (unsigned int)(-value);
        return value;
    }
    int alignDivisor(unsigned int dividend, unsigned int & divisor)
    {
        int count = 0;
        if(divisor >= dividend)
            return count;
        while(divisor <= dividend)
        {
            if(divisor==dividend)    
                return count;
            divisor = divisor << 1;
            count++;
        }
        divisor = divisor >> 1;
        count --;
        return count;
    }
public:
    int divide(int dividend, int divisor) {
     if(divisor == 0)
        return INT_MAX;
     int sign = checkSign(dividend,divisor)  ;
     //cout<<"sign = " << sign <<endl;
     unsigned int dividend_un, divisor_un;
     dividend_un = i2unsigned(dividend);
     divisor_un  = i2unsigned(divisor);
     //cout<<"dividend_un = " << dividend_un << endl;
     //cout<<"divisor_un = "  << divisor_un  << endl;
     
     int count = alignDivisor(dividend_un,divisor_un);
     //cout<<"shift    count = " << count          << endl;
     //cout<<"shifted divisor_un = " << divisor_un << endl;
     
     unsigned int result_un = 0;
     for(int i = count; i >= 0; i --)
     {
         if(dividend_un >= divisor_un)
         {
             result_un   += 1 << i;
             dividend_un -= divisor_un;
         }
         divisor_un = divisor_un >> 1;
        // cout<<"i=" <<i<<"  "<<"result_un="<<result_un<<endl;
     }
     //cout<<"result_un = " << result_un <<endl;
     if(result_un >= 0x80000000 && sign == 1)
        return INT_MAX;
     if(sign == -1)
        return (int)(-result_un);
     return (int)(result_un);
    }
};