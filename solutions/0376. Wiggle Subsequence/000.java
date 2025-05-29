class Solution {
    int sign(int val) {
        if(val==0)
            return 0;
        if(val<0)
            return -1;
        return 1;
    }
    public int wiggleMaxLength(int[] nums) {
        if(nums.length<=1)
            return nums.length;
        int count = nums.length;
        int sign_prev = -2;        
        for(int i=1;i<nums.length;i++) {
            int sign_new = sign(nums[i]-nums[i-1]);
            if(sign_new==0 ) {
                count --;                
                continue;
            }
            if(sign_new!=sign_prev) {
                sign_prev = sign_new; 
                continue;
            }   
            count--;
        }
        return count;
    }
}