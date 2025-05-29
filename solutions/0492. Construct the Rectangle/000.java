class Solution {
    public int[] constructRectangle(int area) {
         int W_Max =(int) Math.round(Math.floor(Math.sqrt(area)));
            int W=W_Max,  L = 0;
            for(;W>=1;W--) {
                if(area%W==0) {
                    int [] WL = {area/W,W};
                    return WL;
                }
            }
            return null;
    }
}