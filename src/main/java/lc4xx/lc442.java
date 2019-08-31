package lc4xx;

import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

public class lc442 {
    @Test
    public void test() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        findDuplicates(nums);
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (true) {
                int v = nums[i];
                if (i + 1 == v || nums[v - 1] == v) break;
                int tmp = nums[v - 1];
                nums[v - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i])
                results.add(nums[i]);
        }
        return results;
    }
}
