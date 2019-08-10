import org.junit.Test;

import java.util.Arrays;

public class lc532 {
    private int nextNum(int nums[], int i) {
        while (i + 1 < nums.length && nums[i + 1] == nums[i])
            i++;
        return i + 1;
    }

    public int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        Arrays.sort(nums);
        int i = 0, j = 1;
        int count = 0;
        while (i < nums.length && j < nums.length) {
            int diff = nums[j] - nums[i];
            if (diff == k) {
                count++;
                i = nextNum(nums, i);
                j = nextNum(nums, j);
            } else if (diff > k) {
                i = nextNum(nums, i);
            } else {
                j = nextNum(nums, j);
            }
            if (i == j) j++;
        }
        return count;
    }
}
