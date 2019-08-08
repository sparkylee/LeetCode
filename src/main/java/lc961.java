import org.junit.Test;

public class lc961 {
    public int repeatedNTimes(int[] A) {
        for (int i = 0; i < A.length; i += 2) {
            if (A[i] == A[i + 1])
                return A[i];
        }
        if (A[0] == A[2] || A[0] == A[3]) return A[0];
        return A[1];
    }
}
