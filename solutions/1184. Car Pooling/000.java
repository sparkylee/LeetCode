class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] passengers = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int[] trip = trips[i];
            for (int j = trip[1]; j < trip[2]; j++) {
                passengers[j] += trip[0];
                if (passengers[j] > capacity) return false;
            }
        }
        return true;
    }
}