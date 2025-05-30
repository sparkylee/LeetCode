class Solution {
    public int smallestRangeI(int[] A, int K) {
         int min = A[0], max=min;
        for (int x : A)
        {
            min = Math.min(x, min);
            max = Math.max(x, max);
        }     
        int delta = max - K - (min + K);
        if (delta < 0)
            delta = 0;
        return delta;
    }
}