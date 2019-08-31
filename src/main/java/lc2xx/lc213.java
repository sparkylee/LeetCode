package lc2xx;

import org.junit.Test;

public  class lc213
{


    @Test
    public void tet1() {
        tc(new int[]{2,3,1,2,4,3});
        tc(new int[]{2,3,2});
        tc(new int[]{1,2,3,1});
        tc(new int[]{2});
        tc(new int[]{1,2});
        tc(new int[]{2,1});
        tc(new int[]{1,3,1,3,100});
        tc(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5});
        tc(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

    }

    private void tc(int[] nums) {
        Solution sol = new Solution();
        int result = sol.rob(nums);
        System.out.println(result);
    }

    class Solution {
        int [] robery0,robery1;

        private int rob(int[] nums, int start, int end) {
            if(start>end) return 0;
            int value = (end==nums.length-2)?robery0[start]:robery1[start];
            if(value>=0) return value;
            int sum1 = rob(nums,start+2,end) + nums[start];

            if (start + 1 > end) {
                if(end==nums.length-2)
                    robery0[start] = value;
                else
                    robery1[start] = value;
                return sum1;
            }
            int sum2 = 0 ;
            if(( nums[start-1]>=nums[start] && nums[start+1]>nums[start]) ||
                    (nums[start - 1] <= nums[start - 2] && nums[start - 1] < nums[start]))
                sum2 = nums[start+1] + rob(nums,start+3,end);
            value = Math.max(sum1,sum2);
            if(end==nums.length-2)
                robery0[start] = value;
            else
                robery1[start] = value;
            return value;
        }

        public int rob(int[] nums) {
            if(nums==null || nums.length<1) return 0;
            if(nums.length==1) return nums[0];
            robery0 = new int[nums.length];
            robery1 = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                robery0[i]=-1;
                robery1[i]=-1;
            }
            int sum1 = rob(nums,2,nums.length-2)+nums[0];
            int sum2 = rob(nums,3,nums.length-1)+nums[1];
            int sum12 = Math.max(sum1,sum2);
            if(nums.length<=2)
                return sum12;
            int sum3 = rob(nums,4,nums.length-1)+nums[2];
            return Math.max(sum12,sum3);
        }
    }
}
