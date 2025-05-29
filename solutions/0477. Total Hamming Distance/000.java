class Solution {
    public int totalHammingDistance(int[] nums) {
        int mask = 0x1;  
        int distance = 0;      
        for(int i=0;i<32;i++) {
            int zeros = 0;
            for(int n: nums) {
                zeros += ((n & mask) ==0)?1:0;
            }
            if(nums.length>zeros)
                distance += (zeros * (nums.length - zeros));
            mask <<= 1;
        }
        return distance;
    }
}