class Solution {
    public int reversePairs(int[] nums) {
        if(nums.length<=1) return 0;
          long [] nums_shift = new long[nums.length];
            for(int i=0;i<nums.length;i++)
                nums_shift[i]=((long)nums[i])*2;
            int count = 0;
            for(int i=0;i<nums.length;i++)
            {
                long ni = (long)nums[i];
                for(int j=i+1;j<nums.length;j++)
                {

                    if(ni>nums_shift[j]) count++;
                }
            }
            return count;
        
    }
}