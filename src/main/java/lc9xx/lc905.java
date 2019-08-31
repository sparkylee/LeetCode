package lc9xx;

public class lc905 {
    void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (true) {
            while (i < A.length && A[i] % 2 == 0) i++;
            while (j >= 0 && A[j] % 2 == 1) j--;
            if (i < j)
                swap(A, i, j);
            else
                return A;
        }
    }
}
