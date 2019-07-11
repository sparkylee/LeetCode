public class lc832 {
    private void swap(int[] row, int i, int j) {
        int tmp = row[i];
        row[i] = row[j];
        row[j] = tmp;
    }

    private void reverse(int[] row) {
        for (int i = 0; i < row.length / 2; i++)
            swap(row, i, row.length - 1 - i);
    }

    private void inverse(int[] row) {
        for (int i = 0; i < row.length; i++)
            row[i] = 1 - row[i];
    }

    public int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            reverse(A[i]);
            inverse(A[i]);
        }
        return A;
    }
}
