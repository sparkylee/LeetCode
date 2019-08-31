package lc7xx;

public class lc766 {

    private boolean isDiagonalEqual(int[][] matrix, int ii, int jj) {
        int i = ii;
        int j = jj;
        while (i < matrix.length && j < matrix[i].length) {
            if (matrix[i][j] != matrix[ii][jj])
                return false;
            i++;
            j++;
        }
        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int ii = 0; ii < matrix.length; ii++) {
            if (!isDiagonalEqual(matrix, ii, 0)) return false;
        }
        for (int jj = 1; jj < matrix[0].length; jj++) {
            if (!isDiagonalEqual(matrix, 0, jj)) return false;
        }
        return true;
    }

}
