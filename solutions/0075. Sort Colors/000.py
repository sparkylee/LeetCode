class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        color=[0]*3;
        for i in nums:
            color[i] += 1;
        index_end = 0;
        for k in range(3):
            index_start = index_end;
            index_end = index_start + color[k];
            for i in range(index_start,index_end):
                nums[i] = k;
        return;