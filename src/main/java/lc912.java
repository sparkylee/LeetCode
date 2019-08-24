public class lc912 {
    private int nextNoGreater(int[] nums, int k, int v) {
        while (k >= 0 && nums[k] > v) k--;
        return k;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void sortArray(int[] nums, int start, int end) {
        if (start < 0 || end >= nums.length || start >= end) return;
        int i = start;
        int j = start;
        int v = nums[i];
        int k = end;
        while (true) {
            while (j + 1 <= end && nums[j + 1] <= v) { // extend
                swap(nums, i, j + 1);
                if (nums[i] < v) i++;
                j++;
            }
            k = nextNoGreater(nums, k, v); // seek next
            if (j == k)
                break;
            swap(nums, j + 1, k);
        }
        sortArray(nums, start, i - 1);
        sortArray(nums, j + 1, end);
    }

    public int[] sortArray(int[] nums) {
        return nums;
    }
}
