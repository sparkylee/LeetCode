class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length<1) return 0;
            int [] counts = new int [nums.length];
            for(int i=nums.length-1;i>=0;i--)
            {
                int len = 0;
                int value = nums[i];
                for(int j=i+1;j<counts.length;j++)
                {
                    if(nums[j]>value)
                        len = Math.max(len,counts[j]);
                }
                counts[i]=len+1;
            }
            int len = 0;
            for(int j=0;j<counts.length;j++)
                len = Math.max(len,counts[j]);
            return len;
    }
}