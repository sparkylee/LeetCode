package lc2xx;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public  class lc209
{


    @Test
    public void tet1() {
        tc(7,new int[]{2,3,1,2,4,3});
        tc(7,new int[]{1,1,1});
        tc(3,new int[]{1,1,1});

    }

    private void tc(int s, int[] nums) {
        Solution sol = new Solution();
        int result = sol.minSubArrayLen(s,nums);
        System.out.println(result);
    }

    class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            if(nums==null || nums.length<1 ) return 0;
            int i=0,j=0;
            int sum = 0;
            int len = nums.length;
            while (j < nums.length) {
                sum += nums[j];
                while (sum - nums[i] >= s) {
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
}
