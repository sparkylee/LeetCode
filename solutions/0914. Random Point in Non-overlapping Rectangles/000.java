class Solution {
    int [] accumulates;
    Random rand;
    int [] [] rects;
    public Solution(int[][] rects) {
        int [] sums = new int[rects.length];
        this.accumulates = new int[rects.length];
        int accu = 0;
        for(int i=0;i<rects.length;i++){
            int [] rect = rects[i];
            sums[i] = (rect[2] - rect[0] + 1) * (rect[3]-rect[1] + 1);
            accu += sums[i];
            this.accumulates[i] = accu;
        }
        this.rand = new Random();
        this.rects = rects;
    }
    
    public int[] pick() {
        int rv = this.rand.nextInt(this.accumulates[this.accumulates.length-1]);
        int rect_i = this.getGTClosestIndex(rv, 0, this.accumulates.length-1);
        if(rect_i<0) {
            return new int [] {0,0};
        }
        int [] rect = this.rects[rect_i];
        int x_line_size = rect[2] -rect[0] + 1;
        int remainder_value = rv - (rect_i==0?0:this.accumulates[rect_i-1]);
        int x = remainder_value % x_line_size;
        int y = remainder_value / x_line_size;
        return new int[] {rect[0] + x, rect[1]+y};

    }
    int getGTClosestIndex(int rv, int start, int end){
        if(start<0 || end >= this.accumulates.length || start>end)
            return -1;
        int middle = (start + end) /2;
        if(this.accumulates[middle]>rv){
            int index = getGTClosestIndex(rv, start, middle-1);
            return index==-1?middle:index;
        }        
        return getGTClosestIndex(rv, middle+1, end);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */