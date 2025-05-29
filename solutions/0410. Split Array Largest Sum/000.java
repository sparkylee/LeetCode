class Solution {
    public int splitArray(int[] nums, int m) {
         int n= nums.length;
            long [][]ms=new long[n][m];
            for(int i=0;i<n;i++)
                ms[i][0]= (i==0?nums[i]:(nums[i]+ms[i-1][0]));
            for(int i=0;i<n;i++)
            {
                for(int j=1;j<m;j++)
                {
                    long sum_largest_min = ms[i][j-1];
                    for(int k=i;k>=j;k--)
                    {
                        long sum_right = ms[i][0] - ms[k-1][0];
                        long sum_left_min = ms[k-1][j-1];
                        long sum_larger = Math.max(sum_right,sum_left_min);
                        sum_largest_min = Math.min(sum_larger,sum_largest_min);
                    }
                    ms[i][j]= sum_largest_min;
                }
            }
            return (int)ms[n-1][m-1];
    }
}