class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        i = 0;
        j = len(nums) - 1;
        nL = len(nums);
        while (j >= 0) and (i < len(nums) ) and (i <= j):
            if nums[j] == val:
                j = j - 1;
                nL = nL - 1;
                continue;
            if nums[i] == val:
                nums[i] = nums[j];
                nums[j] = val;
                i = i + 1;
                continue;
            i = i + 1;
        return nL;

