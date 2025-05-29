class Solution {
    public int maxDistToClosest(int[] seats) {
        int disMax = 1;
        int start = -1, end = seats.length;
        for (int i = 0; i <= seats.length; i++) {
            if ((i == seats.length || seats[i] == 1) && i - 1 >= 0 && seats[i - 1] == 0) {
                end = i - 1;
                int len = end - start + 1;
                int dis = (start == 0 || end == seats.length - 1) ? len : (len + 1) / 2;
                disMax = Math.max(disMax, dis);
            }
            if ((i < seats.length && seats[i] == 0) && (i - 1 < 0 || seats[i - 1] == 1)) {
                start = i;
            }
        }
        return disMax;
    }
}