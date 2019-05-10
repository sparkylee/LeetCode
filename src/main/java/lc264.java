import org.junit.Test;

public  class lc264
{

    @Test
    public void test1()
    {
        testcase(9);
    }

    private void testcase(int   n)
    {
        Solution s = new Solution();
        int result = s.nthUglyNumber(n);
        System.out.println(result);
    }

    class Solution {
        public int nthUglyNumber(int n) {
            int [] uglyNumbers = new int[n];
            uglyNumbers[0] = 1;
            int k2 = 0, k3 = 0, k5 = 0 ;
            int d2 = 2,d3 = 3, d5 = 5;
            for(int i=1;i<n;i++)
            {
                int d = Math.min(Math.min(d2,d3),d5);
                uglyNumbers[i] = d;
                while(d>=d2)
                {
                    k2++;
                    d2= uglyNumbers[k2]*2;
                }
                while(d>=d3)
                {
                    k3++;
                    d3= uglyNumbers[k3]*3;
                }
                while(d>=d5)
                {
                    k5++;
                    d5= uglyNumbers[k5]*5;
                }

            }
            return uglyNumbers[n-1];
        }
    }

}
