package lc5xx;

import org.junit.Test;

public class lc566 {
    @Test
    public void test() {
        t(new int[][]{{1, 2}, {3, 4}}, 1, 4);
        t(new int[][]{{1, 2}, {3, 4}}, 2, 4);
    }

    private void t(int[][] nums, int r, int c) {
        printArray(nums);
        int[][] m = matrixReshape(nums, r, c);
        printArray(m);
        System.out.println("done");
    }

    private void printArray(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            int[] row = nums[i];
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int[][] m = new int[r][c];
        int ri = 0, ci = 0;
        for (int i = 0; i < nums.length; i++) {
            int[] row = nums[i];
            for (int j = 0; j < row.length; j++) {
                if (ri >= r || ci >= c) {
                    return nums;
                }
                m[ri][ci] = row[j];
                ci++;
                if (ci >= c) // reset ri and ci
                {
                    ri++;
                    ci = 0;
                }
            }
        }
        if (ri == r && ci == 0) {
            return m;
        }
        return nums;
    }
}
