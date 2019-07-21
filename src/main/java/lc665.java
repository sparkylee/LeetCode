import org.junit.Test;

public class lc665 {
    private boolean isNondecreasing(int[] nums, int start) {
        if (nums == null || start >= nums.length - 1) return true;
        for (int i = start; i < nums.length; i++)
            if (nums[i] < nums[i - 1])
                return false;
        return true;
    }

    private boolean isLeftSingular(int[] nums, int i) {
        return (i - 1 < 0 || nums[i - 1] > nums[i]);
    }

    private boolean isRightSingular(int[] nums, int i) {
        return (i + 1 >= nums.length || nums[i + 1] < nums[i]);
    }

    private boolean isSingular(int[] nums, int i) {
        return isLeftSingular(nums, i) && isRightSingular(nums, i);
    }

    public boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (!isNondecreasing(nums, i + 2))
                    return false;
                for (int k = 0; k <= 1; k++)
                    if ((i - 1 < 0 || nums[i - 1] <= nums[i + k]) &&
                            (i + 2 >= nums.length || nums[i + 2] >= nums[i + k]))
                        return true;
                return false;
            }
        }
        return true;
    }
}
