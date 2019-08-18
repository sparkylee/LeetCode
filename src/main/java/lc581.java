import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc581 {
    @Test
    public void test() {
//        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] nums = {1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5};
        findUnsortedSubarray(nums);

    }
    private int getMin(int[] nums, int start, int end) {
        int m = nums[start];
        for (int i = start; i <= end; i++)
            if (nums[i] < m)
                m = nums[i];
        return m;
    }

    private int getMax(int[] nums, int start, int end) {
        int m = nums[start];
        for (int i = start; i <= end; i++)
            if (nums[i] > m)
                m = nums[i];
        return m;
    }

    private int getFirstGreaterIndex(int[] nums, int start, int end, int target) {
        if (start > end) return -1;
        if (start == end)
            return nums[start] > target ? start : -1;
        int middle = (start + end) / 2;
        if (nums[middle] <= target)
            return getFirstGreaterIndex(nums, middle + 1, end, target);
        return getFirstGreaterIndex(nums, start, middle, target);
    }

    private int getLastSmallerIndex(int[] nums, int start, int end, int target) {
        if (start > end) return -1;
        if (start == end)
            return nums[start] < target ? start : -1;
        int middle = (start + end + 1) / 2;
        if (nums[middle] >= target)
            return getLastSmallerIndex(nums, start, middle - 1, target);
        return getLastSmallerIndex(nums, middle, end, target);
    }

    public int findUnsortedSubarray(int[] nums) {
        int start = 0;
        while (start + 1 < nums.length) {
            if (nums[start] <= nums[start + 1])
                start++;
            else
                break;
        }
        if (start == nums.length - 1) return 0;
        int end = nums.length - 1;
        while (end - 1 >= 0) {
            if (nums[end] >= nums[end - 1])
                end--;
            else
                break;
        }
        if (end == 0) return 0;
        int min = getMin(nums, start, end);
        int max = getMax(nums, start, end);
        int left = getFirstGreaterIndex(nums, 0, start - 1, min);
        int right = getLastSmallerIndex(nums, end + 1, nums.length - 1, max);
        start = left == -1 ? start : left;
        end = right == -1 ? end : right;
        return end - start + 1;
    }

}
