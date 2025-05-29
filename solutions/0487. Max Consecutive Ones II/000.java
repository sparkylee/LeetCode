class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int [] zp = new int[] {-1,-1}; // zero positions, first and second
        int max_ones = 1;
        for(int i=0;i<nums.length;i++) {
            int num = nums[i];
            int max_ones_current;;           
            int zero_count = 0;
            int bonus;
            if(num==1) {
                bonus = (zp[0]!=-1 && zp[1]==-1)? 1: 0;
                max_ones_current = i - zp[0] + bonus;
            } else {
                bonus = zp[1]!=-1 ? -1: 0; 
                max_ones_current = i - zp[0] + bonus;
            }
            max_ones = Math.max(max_ones_current, max_ones);
            // System.out.println("i="+i+" zero_count="+zero_count+" bonus="+bonus+" max_ones_current="+max_ones_current);
            if(num==0) {
                zp[0] = zp[1];
                zp[1] = i;
            }         
        }
        return max_ones;
    }
}