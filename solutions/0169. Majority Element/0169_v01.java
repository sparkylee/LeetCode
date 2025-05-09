class Solution {
    public int majorityElement(int[] nums) {
        int i = 0, j = 0;
        while (j<nums.length) {
            if(nums[i]==nums[j]) {
                j++;
                continue;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i+=2;
            j = Math.max(j, i);
        }
        return nums[i];
    }
}