class Solution {
    public int arrangeCoins(int n) {
         long sum  = 0;
            long uppper = ((long)n)+1;
            for(int i=1;i<=uppper;i++)
            {
                sum+=i;
                if(sum>n)
                     return (i-1);
            }
            return 0;
    }
}