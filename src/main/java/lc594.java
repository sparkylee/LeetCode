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
            int count = 0;
            while (j < nums.length && nums[j] - nums[i] <= 1)
                j++;
            countMax = Math.max(j - i, countMax);
            i++;
            while (i < nums.length && nums[i] == nums[i - 1])
                i++;
        }
        return countMax;
    }

}
