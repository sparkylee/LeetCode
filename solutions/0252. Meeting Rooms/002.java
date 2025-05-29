class Solution {
     public boolean canAttendMeetings(int[][] intervals) {
        BitSet bits = new BitSet(1000000);
        for(int i = 0; i < intervals.length; i++) {
            BitSet bits_i = bits.get(intervals[i][0],intervals[i][1]);
            if(bits_i.cardinality()!=0)
                return false;
            bits.set(intervals[i][0],intervals[i][1]);
        }
        return true;
    }
}