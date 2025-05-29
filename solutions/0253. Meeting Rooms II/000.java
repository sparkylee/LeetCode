class Solution {
    void assignMeeting(List<Integer> rooms, int [] interval) {
        int start = interval[0], end = interval[1];
        int index = -1;
        for(int i=rooms.size()-1;i>=0;i--) {
           if(rooms.get(i)<=start) {
                index = i;
                break;
           }
        }
        if(index==-1) {
            rooms.add(end);
            return;
        }
        rooms.set(index, end);
        while(index<rooms.size()-1) {
            int time1 = rooms.get(index);
            int time2 = rooms.get(index+1);
            if(time1>time2) {                
                rooms.set(index+1,time1);
                rooms.set(index,time2);                
            }
            index++;
        }

    }
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(p -> p[0]));
        List<Integer> rooms = new ArrayList<>();        
        for(int i=0;i<intervals.length;i++) {
            assignMeeting(rooms,intervals[i]);
        }
        return rooms.size();
        
    }
}