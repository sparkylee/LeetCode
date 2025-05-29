class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
         int m = 0,count=0;
            for(int i=0;i<nums.length;i++)
            {
                if(count>m) m= count;
                if(nums[i]==0)
                    count = 0;
                else
                    count++;

            }
            if(count>m) m= count;
            return m;
    }
}