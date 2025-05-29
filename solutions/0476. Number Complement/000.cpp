class Solution {    
public:
    int findComplement(int num) {
        int i = 31;
        int maskBits = 0xffffffff;
        for(;i>=0;i--)
        {
            int mask = 1 << i;
            int mResult = mask & num;
            if(mResult != 0)
                break;
            else
                maskBits ^= mask;
        }
        num = ~num;
        int results =  num & maskBits;
        return results;
            
    }
};