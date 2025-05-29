class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        i=0;
        j=1;
        n=len(nums);
        if n <=1:
            return;
        while i <n-1 and j <= n-1:
            if nums[i] != 0:
                i = i + 1;
                j = j + 1;
                continue;
            if nums[j] != 0:
                nums[i] = nums[j];
                nums[j] = 0;
                i = i + 1;
                j = j + 1;
                continue;
            j = j + 1;
        return;
        