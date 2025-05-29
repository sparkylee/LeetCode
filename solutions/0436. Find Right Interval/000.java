class Solution {
    int searchMinimized(int [][] itvls, int start, int end, int val) {
        if(start<0 || end >= itvls.length || start>end){
            return -1;
        }
        int middle = (start+end)/2;
        if(itvls[middle][0]>=val) {
            int index = searchMinimized(itvls, start, middle-1,val);
            return index==-1 ? itvls[middle][2] : index;
        }
        return searchMinimized(itvls, middle+1, end,val);
    }
    public int[] findRightInterval(int[][] intervals) {
        int [][] itvls = new int[intervals.length][3];
        for(int i=0;i<intervals.length;i++) {
            itvls[i][0] = intervals[i][0];
            itvls[i][1] = intervals[i][1];
            itvls[i][2] = i;
        }
        Arrays.sort(itvls, Comparator.comparingInt(a -> a[0]));
        int [] results = new int[intervals.length];
        for(int i=0;i<itvls.length;i++) {
            results[itvls[i][2]]=searchMinimized(itvls, i, itvls.length-1, itvls[i][1]);
        }
        return results;
    }
}