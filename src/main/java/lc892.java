public class lc892 {
    int[][] diffs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int getHeight(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return 0;
        return grid[i][j];
    }

    private int countSurfaceSide(int[][] grid, int i, int j, int x, int y) {
        int delta = grid[i][j] - grid[x][y];
        return delta >= 0 ? delta : 0;
    }

    private int countSurfaceOfGrid(int[][] grid, int i, int j) {
        int height = getHeight(grid, i, j);
        if (height == 0) return 0;
        int count = 2;
        for (int[] d : diffs)
            count += countSurfaceSide(grid, i, j, i + d[0], j + d[1]);
        return count;
    }

    public int surfaceArea(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                count += countSurfaceOfGrid(grid, i, j);
            }
        }
        return count;
    }
}
