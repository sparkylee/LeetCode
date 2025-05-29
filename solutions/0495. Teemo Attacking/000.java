class Solution {
    int findPoisonedDuration(int[] timeSeries, int i, int duration) {
        if(i >= timeSeries.length) {
            return 0;
        }
        int t_previous = timeSeries[i];
        int delta_from_i = 0;
        int delta = 0;
        while(i<timeSeries.length && ((delta=timeSeries[i] - t_previous) + 1) <= duration  ) {
            delta_from_i += delta;
            t_previous = timeSeries[i];
            i++;
        } 
        int duration_from_i = delta_from_i + duration;
        return duration_from_i + findPoisonedDuration(timeSeries, i, duration);
    }
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(duration ==0) {
            return 0;
        }
        return findPoisonedDuration(timeSeries,0, duration);
    }
}