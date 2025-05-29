class Solution {
    public int findMin(int[] nums) {
       
            int m = nums[0];
            for(int n : nums)
            {
                if(n<m) m=n;
            }
            return m;
        
    }
}