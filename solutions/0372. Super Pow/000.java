class Solution {
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