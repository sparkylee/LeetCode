import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc994 {
    @Test
    public void test() {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        orangesRotting(grid);
    }

    class Coord {
        int i, j;

        Coord(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private boolean isFresh(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length
                && j >= 0 && j < grid[0].length && grid[i][j] == 1;
    }

    private void addAdjacentIfFresh(int[][] grid, List<Coord> rottens_new,
                                    Coord orange, int delta_i, int delta_j) {
        if (isFresh(grid, orange.i + delta_i, orange.j + delta_j))
            rottens_new.add(new Coord(orange.i + delta_i, orange.j + delta_j));
    }

    public int orangesRotting(int[][] grid) {
        List<Coord> rottens = new ArrayList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == 2)
                    rottens.add(new Coord(i, j));
        int count = 0;
        while (true) {
            List<Coord> rottens_new = new ArrayList<>();
            for (int i = 0; i < rottens.size(); i++) {
                Coord orange = rottens.get(i);
                addAdjacentIfFresh(grid, rottens_new, orange, -1, 0);
                addAdjacentIfFresh(grid, rottens_new, orange, 1, 0);
                addAdjacentIfFresh(grid, rottens_new, orange, 0, -1);
                addAdjacentIfFresh(grid, rottens_new, orange, 0, 1);
            }
            count++;
            if (rottens_new.isEmpty()) break;
            rottens = rottens_new;
        }
        return count;
    }
}
