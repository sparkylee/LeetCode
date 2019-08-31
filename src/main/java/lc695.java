import org.junit.Test;

public class lc695 {
    public int maxAreaOfIslandAt(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) return 0;
        grid[i][j] = 0;
        int areaL = maxAreaOfIslandAt(grid, i - 1, j);
        int areaR = maxAreaOfIslandAt(grid, i + 1, j);
        int areaT = maxAreaOfIslandAt(grid, i, j - 1);
        int areaB = maxAreaOfIslandAt(grid, i, j + 1);
        return 1 + areaL + areaR + areaT + areaB;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int area = maxAreaOfIslandAt(grid, i, j);
                if (area > maxArea) maxArea = area;
            }

        }
        return maxArea;
    }
}
