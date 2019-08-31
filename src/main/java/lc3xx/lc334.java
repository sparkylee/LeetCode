package lc3xx;

import org.junit.Test;

public class lc334 {

    @Test
    public void t1() {
        test(new int[]{1, 2, 3, 4, 5});
        test(new int[]{1, 2, -2, -1, 5});
        test(new int[]{1, 10, 4, -1, 5, 6});

        ///
        test(new int[]{5, 4, 3, 2, 1});
        test(new int[]{5, 3, 4, 2, 3, 0});
    }

    private void test(int[] nums) {
        System.out.println(increasingTriplet(nums));
    }

    /////////////////////////////
    class Axis {
        int i = 0, j = 0;
    }

    private boolean seekIncreasingPair(int[] nums, int start, final Axis x) {
        if (start < 0 || start >= nums.length) return false;
        x.i = start;
        x.j = x.i + 1;
        while (x.j < nums.length && nums[x.j] <= nums[x.i]) {
            x.i = x.j;
            x.j++;
        }
        return x.j < nums.length;
    }

    public boolean increasingTriplet(int[] nums) {
        Axis a = new Axis(), b = new Axis();
        boolean isFound = seekIncreasingPair(nums, 0, a);
        if (!isFound) return false;
        b.i = a.j + 1;
        while (b.i < nums.length) {
            if (nums[b.i] > nums[a.j])
                return true;
            if (nums[b.i] > nums[a.i]) {
                a.j = b.i;
                b.i = a.j + 1;
                continue;
            }
            // nums[k] < nums[i]
            //look for another value that is smaller than nums[j]
            isFound = seekIncreasingPair(nums, b.i, b);
            if (!isFound) return false;
            if (nums[b.j] > nums[a.j])
                return true;
            a.i = b.i;
            a.j = b.j;
            b.i = a.j + 1;
        }
        return false;
    }

}
