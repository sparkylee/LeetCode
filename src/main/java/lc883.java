import java.util.ArrayList;
import java.util.List;

public class lc883 {
    public int projectionArea(int[][] grid) {
        int xy = 0, yz = 0, xz = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 0)
                    xy++;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            int value = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > value)
                    value = grid[i][j];
            }
            yz += value;
        }
        for (int j = 0; j < grid[0].length; j++) {
            int value = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] > value)
                    value = grid[i][j];
            }
            xz += value;
        }
        return xy + yz + xz;
    }
}
