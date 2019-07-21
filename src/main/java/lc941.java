import org.junit.Test;

public class lc941 {
    @Test
    public void test() {
        System.out.println(validMountainArray(new int[]{0, 3, 2, 1}));
    }

    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) return false;
        int m = A[0], mi = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > m) {
                mi = i;
                m = A[i];
            }
        }
        if (mi <= 0 || mi >= A.length - 1) return false;
        for (int i = 1; i <= mi; i++) {
            if (A[i] <= A[i - 1]) return false;
        }
        for (int i = mi; i < A.length - 1; i++) {
            if (A[i] >= A[i + 1]) return false;
        }
        return true;
    }
}
