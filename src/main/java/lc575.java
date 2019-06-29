import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class lc575 {
    @Test
    public void test() {
        t(new int[]{1, 1, 2, 2, 3, 3});
        t(new int[]{1, 1, 2, 3});
    }

    private void t(int[] nums) {
        System.out.println(distributeCandies(nums));
    }

    public int distributeCandies(int[] candies) {
        Set<Integer> candieSet = new HashSet<>();
        for (int c : candies) {
            candieSet.add(c);
        }
        return Math.min(candies.length / 2, candieSet.size());
    }

}
