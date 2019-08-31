package lc10xx;

import org.junit.Test;

public class lc1094 {
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
