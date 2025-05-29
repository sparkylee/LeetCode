class Solution {
    public int[] singleNumber(int[] nums) {       
       int xsum = 0;
       for(int n: nums) {
          xsum ^= n;
       }        
       int xdiff = 0x1;
       while((xsum & xdiff) == 0)
        xdiff <<=1;       
    int x0 = 0, x1 = 0;
    for(int n: nums) {
        if((xdiff & n) == 0) {
            x0 ^= n;
        } else {
            x1 ^= n;
        }
    }
    return new int [] {x0, x1};
       
    }
}