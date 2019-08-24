import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

public class lc442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 == nums[i]) continue;
            int v = nums[i];
            if (nums[v - 1] == nums[i]) {
                results.add(v);
            } else {
                int tmp = nums[v - 1];
                nums[v - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        return results;
    }
}
