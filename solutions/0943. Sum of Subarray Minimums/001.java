class Solution {
    private int mod(long x) {
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
        long a = mi - start;
        long b = end - mi;
        long count = mv * (a + 1) * (b + 1);
        long countLeft = sumSubarrayMins(A, start, mi - 1);
        long countRight = sumSubarrayMins(A, mi + 1, end);
        return mod(count + countLeft + countRight);
    }

    public int sumSubarrayMins(int[] A) {
        return sumSubarrayMins(A, 0, A.length - 1);
    }
}