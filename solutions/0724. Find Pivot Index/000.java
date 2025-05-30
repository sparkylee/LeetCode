class Solution {
    public int pivotIndex(int[] nums) {
         if (nums == null || nums.length == 0)
            return -1;

        int sum = 0;
        for (int n : nums)
            sum += n;
        int ls = 0;
        int rs = sum - nums[0];
        if (ls == rs) return 0;
        for (int i = 1; i < nums.length; i++) {
            ls += nums[i - 1];
            rs -= nums[i];
            if (ls == rs) return i;
        }
        return -1;
    }
}