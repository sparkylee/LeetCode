package lc2xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc228
{
    @Test
    public void test1() {
        test(new int[] {0,1,2,4,5,7});
        test(new int[] {});
        test(new int[] {7});
        test(new int[] {7,9});
        test(new int[] {6,7,9});
    }

    private void test(int[] nums) {
        Solution s = new Solution();
        List<String> summary = s.summaryRanges(nums);
        for(String str: summary) System.out.print(str+" ");
        System.out.println();
    }

    class Solution {
        private String getStr(int start, int end) {
            if(start==end) return String.valueOf(start);
            return String.valueOf(start)+"->"+String.valueOf(end);
        }

        public List<String> summaryRanges(int[] nums) {
            List<String> summary = new ArrayList<>();
            if(nums==null || nums.length <1) return summary;
            int head = nums[0],tail = nums[0];
            for (int i = 0; i < nums.length; i++) {
                if(nums[i]==tail) continue;
                if (nums[i] == tail + 1) {
                    tail = nums[i];
                    continue;
                }

                summary.add(getStr(head,tail));
                head = nums[i];
                tail = nums[i];
            }
            summary.add(getStr(head,tail));
            return summary;
        }
    }

}
