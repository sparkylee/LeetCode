class Solution {
     public boolean canAttendMeetings(int[][] intervals) {
        BitSet bits = new BitSet(1000000);
        for(int i = 0; i < intervals.length; i++) {
            for(int j = intervals[i][0]; j < intervals[i][1]; j++) {
                if(bits.get(j)) {
                    return false;
                }
                bits.set(j);
            }
        }
        return true;
    }
}