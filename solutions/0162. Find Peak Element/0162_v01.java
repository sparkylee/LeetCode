class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length -1;
        while(true)
        {
            if(start==end) return  start;
            if(start==end - 1) return nums[start]>nums[end]?start:end;
            int middle = (start+end)/2;
            if(nums[middle]<nums[start])
            {
                end = middle;
                continue;
            }
            if(nums[middle]<nums[end])
            {
                start = middle;
                continue;
            }
            int mv = nums[middle];
            int left = nums[middle-1],right = nums[middle+1];
            if(left>mv)
            {
                end = middle;
                continue;
            }
            if(right>mv)
            {
                start= middle;
                continue;
            }
            return middle;
        }
    }
}