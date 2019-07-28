public class lc840 {
    private boolean isMagicSqare(int[][] grid, int row, int col) {
        boolean[] checked = new boolean[9];
        if (row < 0 || row >= grid.length - 2 || col < 0 || col >= grid[0].length - 2)
            return false;
        for (int i = row; i < row + 3; i++) {
            int sum = 0;
            for (int j = col; j < col + 3; j++) {
                int x = grid[i][j];
                if (x < 1 || x > 9 || checked[x - 1])
                    return false;
                checked[x - 1] = true;
                sum += x;
            }
            if (sum != 15) return false;
        }
        for (int i = col; i < col + 3; i++) {
            int sum = 0;
            for (int j = row; j < row + 3; j++)
                sum += grid[j][i];
            if (sum != 15) return false;
        }
        int y1 = 0;
        for (int i = 0; i < 3; i++)
            y1 += grid[row + i][col + i];
        if (y1 != 15) return false;
        y1 = 0;
        for (int i = 0; i < 3; i++)
            y1 += grid[row + i][col + 3 - i];
        if (y1 != 15) return false;
        return true;
    }

    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[i].length - 2; j++) {
                if (isMagicSqare(grid, i, j))
                    count++;
            }
        }
        return count;
    }
}
