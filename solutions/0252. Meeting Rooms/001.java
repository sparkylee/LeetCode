class Solution {
     public boolean canAttendMeetings(int[][] intervals) {
        BitSet bits = new BitSet(1000000);
        int cardinality = 0;
        for(int i = 0; i < intervals.length; i++) {
            bits.set(intervals[i][0],intervals[i][1]);
            int cardinality_new = bits.cardinality();
            if(cardinality_new!=cardinality+intervals[i][1]-intervals[i][0])            {
                return false;
            }
            cardinality=cardinality_new;
        }
        return true;
    }
}