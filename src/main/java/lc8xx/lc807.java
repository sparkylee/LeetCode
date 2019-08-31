package lc8xx;

public class lc807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] hhs = new int[grid.length];
        int[] vhs = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            hhs[i] = grid[i][0];
            for (int j = 0; j < grid[0].length; j++)
                hhs[i] = Math.max(hhs[i], grid[i][j]);
        }
        for (int j = 0; j < grid[0].length; j++) {
            vhs[j] = grid[0][j];
            for (int i = 0; i < grid.length; i++)
                vhs[j] = Math.max(vhs[j], grid[i][j]);
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int m = Math.min(hhs[i], vhs[j]);
                count += m - grid[i][j];
            }
        }
        return count;
    }
}
