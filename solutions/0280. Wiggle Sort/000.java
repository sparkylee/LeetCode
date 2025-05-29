class Solution {
    void swap(int [] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    void wiggleSort(int [] nums, int i) {
       
    }
    public void wiggleSort(int[] nums) {
        int i = 0;
        while(true) {
        if(i>=nums.length-1) return;
        if(nums[i] > nums[i+1]) {
            swap(nums, i, i+1);
        }
        i++;
        if(i>=nums.length-1) {
            return;
        };
        if(nums[i]< nums[i+1]) {
            swap(nums, i, i+1);
        }
        i++;
        }
    }
}