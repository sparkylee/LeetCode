import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc372
{
    @Test
    public void test1()
    {
        test(2147483647,new int[]{2,0,0});
        test(2,new int []{1,0});
        test(2,new int []{3});
    }

    private void test(int a, int [] b )
    {
        Solution s = new Solution();
       int result = s.superPow(a,b);
        System.out.println("dfasd:"+result);
    }
    class Solution
    {
        public int superPow(int a, int[] b) {
            int mod_total = 1;
            int ai = a%1337;
            for(int i = b.length -1 ;i>=0;i--)
            {

                int mod_current = powMod(ai,b[i],1337);
                mod_total = (mod_current*mod_total)%1337;
                ai = powMod(ai,10,1337);
            }
            return mod_total;
        }
        private int powMod(int a,int b,int c)
        {
            int x =1 ;
            for(int i=0;i<b;i++)
            {
                x= (x*a)%c;
            }
            return x;
        }
    }



}
