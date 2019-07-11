public class lc922 {
    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1;
        while (true) {
            while (i < A.length && A[i] % 2 == 0) {
                i += 2;
            }
            while (j < A.length && A[j] % 2 == 1) {
                j += 2;
            }
            if (i < A.length && j < A.length)
                swap(A, i, j);
            else
                break;
        }
        return A;
    }
}
