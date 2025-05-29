class Solution {
    public int rangeBitwiseAnd(int m, int n) {
          int mask = 0x80000000;
            int x = m ^ n;
            if(x==0) return m;
            for(int i=0;i<32;i++)
            {
                if((x&mask) != 0)
                    break;
                mask = mask >> 1;
            }
            mask = mask<<1;
            return mask&m;
    }
}