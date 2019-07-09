public class lc766 {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int i = 0, j = 0;
        for (int ii = 0; ii < matrix.length; ii++) {
            i = ii;
            j = 0;
            while (i < matrix.length && j < matrix[i].length) {
                if (matrix[i][j] != matrix[ii][0])
                    return false;
                i++;
                j++;
            }
        }
        return true;
    }

}
