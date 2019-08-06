import org.junit.Test;

public class lc977 {
    public int[] sortedSquares(int[] A) {
        int n = -1, p = A.length;
        for (int i = 0; i < A.length; i++) {
            n = i;
            if (A[i] >= 0) {
                n = i - 1;
                p = i;
                break;
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        int[] B = new int[A.length];
        int k = 0;
        while (true) {
            int n2 = -1, p2 = -1;
            if (n >= 0) n2 = A[n];
            if (p < A.length) p2 = A[p];
            if (n2 < 0 && p2 < 0) break;
            if (p2 < 0 || (p2 >= n2 && n2 >= 0)) {
                B[k] = A[n];
                k++;
                n--;
                continue;
            }
            if (n2 < 0 || (n2 >= p2 && p2 >= 0)) {
                B[k] = A[p];
                k++;
                p++;
                continue;
            }
        }
        return B;
    }
}
