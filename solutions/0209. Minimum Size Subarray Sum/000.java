class Solution {
    public int minSubArrayLen(int s, int[] nums) {
     if(nums==null || nums.length<1 ) return 0;
            int i=0,j=0;
            int sum = 0;
            int len = nums.length;
            while(j<nums.length)
            {
                sum += nums[j];
                while(sum - nums[i]>=s)
                {
                    sum -= nums[i];
                    i++;
                }
                if(sum>=s)
                    len = Math.min(len,j-i+1);
                j++;
            }
            return sum>=s?len:0;    
    }
}