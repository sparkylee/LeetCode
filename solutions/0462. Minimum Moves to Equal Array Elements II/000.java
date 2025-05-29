class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int middle = nums.length/2;
        int median = nums[middle]; 
        if(nums.length%2==0) {
            median = (median + nums[middle-1])/2;
        }
        int count = 0;
        for(int val: nums) {
            count += Math.abs(val-median);
        }
        return count;
    }
}