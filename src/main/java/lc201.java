import org.junit.Test;

public  class lc201
{
    @Test
    public void test1()
    {
        test(5,7);
        test(0,1);

        test(2147483646,2147483647);
        test(8,9);
        test(2147483647,2147483647);
    }
    private void test(int m,int n)
    {
        Solution s = new Solution();
        int result = s.rangeBitwiseAnd( m,  n);
        System.out.println(result);
    }

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

}
