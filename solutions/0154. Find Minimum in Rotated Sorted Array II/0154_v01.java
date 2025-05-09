class Solution {
    private int findMin(int [] nums,int start,int end)
    {  if(start==end) return nums[start];
        if(end - start ==1  ) return Math.min(nums[start],nums[end]);
        int middle = (start + end)/2;
        while(nums[middle]==nums[end] && end!=middle)
            end --;
        if(nums[middle]>nums[end])
            return findMin(nums,middle,end);
        else
            return findMin(nums,start,middle);

    }
    public int findMin(int[] nums) {
        return findMin(nums,0,nums.length-1);
    }
}