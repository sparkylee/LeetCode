public class lc704 {

    private int search(int[] nums, int start, int end, int target) {
        if (start < 0 || start > end || end >= nums.length) return -1;
        int middle = (start + end) / 2;
        if (nums[middle] == target)
            return middle;
        if (target < nums[middle])
            return search(nums, start, middle - 1, target);
        return search(nums, middle + 1, end, target);

    }

    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

}
