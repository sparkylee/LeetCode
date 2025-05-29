class Solution {
    void swap(int [] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    void wiggleSort(int [] nums, int i) {
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
        wiggleSort(nums,i);
    }
    public void wiggleSort(int[] nums) {
        wiggleSort(nums,0);
    }
}