class Solution {
    public int dominantIndex(int[] nums) {
         int xi = -1, x = 0;
        for (int i = 0; i < nums.length; i++) {
            if (xi == -1 || nums[i] >= x) {
                x = nums[i];
                xi = i;
            }
        }
        int yi = -1, y = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != xi && (yi == -1 || nums[i] >= y)) {
                y = nums[i];
                yi = i;
            }
        }
        if (yi == -1 || x >= y * 2) return xi;
        return -1;
    }
}