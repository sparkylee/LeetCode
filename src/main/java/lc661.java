public class lc661 {
    public class Accumulative {
        int val = 0;
        int count = 0;
    }

    void checkGrid(int[][] M, int i, int j, Accumulative a) {
        if (i < 0 || i >= M.length || j < 0 || j >= M[0].length)
            return;
        a.count++;
        a.val += M[i][j];
    }

    int calcGrid(int[][] M, int i, int j) {
        Accumulative a = new Accumulative();
        for (int di : new int[]{-1, 0, 1}) {
            for (int dj : new int[]{-1, 0, 1}) {
                checkGrid(M, di + i, dj + j, a);
            }
        }
        return (int) Math.floor((double) a.val / (double) a.count);
    }

    public int[][] imageSmoother(int[][] M) {
        int[][] Mn = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                Mn[i][j] = calcGrid(M, i, j);
            }
        }
        return Mn;
    }
}
