package lc6xx;

public class lc674 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        int max = 0;
        int i = 0;
        boolean isInit = true;
        while (i < nums.length) {
            if (isInit) {
                isInit = false;
                count = 1;
            } else {
                if (nums[i] > nums[i - 1]) {
                    count++;
                } else {
                    max = Math.max(count, max);
                    isInit = true;
                    continue;
                }
            }
            i++;

        }
        max = Math.max(count, max);
        return max;
    }
}
