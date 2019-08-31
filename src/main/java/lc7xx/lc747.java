package lc7xx;

import org.junit.Test;

public class lc747 {
    @Test
    public void test() {
        System.out.println(dominantIndex(new int[]{0, 0, 0, 1}));
    }

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
