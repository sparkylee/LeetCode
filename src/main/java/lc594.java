import org.junit.Test;

import java.util.Arrays;

public class lc594 {

    @Test
    public void test() {

    }

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = 0;
        int countMax = 0;
        while (i < nums.length) {
            j = i;
            int count = 0;
            while (j < nums.length && nums[j] - nums[i] <= 1) {
                j++;
                count++;
            }
            countMax = Math.max(count, countMax);
        }
        return countMax;
    }

}
