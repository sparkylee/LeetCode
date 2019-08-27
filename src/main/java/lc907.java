public class lc907 {
    private int mod(long x) {
        if (x < 1000000007) return (int) x;
        return (int) (x % 1000000007);
    }

    private int sumSubarrayMins(int[] A, int start, int end) {
        if (start < 0 || end >= A.length || start > end)
            return 0;
        int mi = start;
        int mv = A[start];
        for (int i = start; i <= end; i++) {
            if (mv > A[i]) {
                mv = A[i];
                mi = i;
            }
        }
        int a = mi - start;
        int b = end - mi;
        long count = mv * (a + 1) * (b + 1);
        int countLeft = sumSubarrayMins(A, start, mi - 1);
        int countRight = sumSubarrayMins(A, mi + 1, end);
        return mod(count + countLeft + countRight);
    }

    public int sumSubarrayMins(int[] A) {
        return sumSubarrayMins(A, 0, A.length - 1);
    }
}
