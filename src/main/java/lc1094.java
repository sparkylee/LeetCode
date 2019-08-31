import org.junit.Test;

public class lc1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        int maxDistance = 0;
        for (int i = 0; i < trips.length; i++) {
            maxDistance = Math.max(maxDistance, trips[i][2]);
        }
        int[] passengers = new int[maxDistance + 1];
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
