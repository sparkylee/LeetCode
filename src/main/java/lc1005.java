import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc1005 {
    public int largestSumAfterKNegations(int[] A, int K) {
        int[] NC = new int[101];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= 0) NC[-A[i]]++;
            sum += A[i];
        }
        int delta = 0;
        for (int i = 100; i > 0; i--) {
            if (NC[i] > 0) {
                int reduction = Math.min(NC[i], K);
                NC[i] = NC[i] - reduction;
                K = K - reduction;
                delta += 2 * reduction * i;
                if (K == 0) {
                    return sum + delta;
                }
            }
        }
        if (K % 2 == 0) return sum + delta;
        int mv = Math.abs(A[0]);
        for (int i = 0; i < A.length; i++) {
            int v = Math.abs(A[0]);
            mv = Math.min(v, mv);
        }
        return sum + delta - mv;
    }
}
