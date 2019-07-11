public class lc852 {
    public int peakIndexInMountainArray(int[] A) {
        int pi = 0, peak = A[pi];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > peak) {
                pi = i;
                peak = A[i];
            }
        }
        return pi;
    }
}
