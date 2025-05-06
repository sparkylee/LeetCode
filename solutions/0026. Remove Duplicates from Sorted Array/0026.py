class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        i = 0;
        j = i;
        if len(nums) < 2:
            return len(nums);
        while (i < len(nums)):
            if (nums[i] != nums[i+1]):
                i = i + 1;
                j = i;
                if i + 1 >= len(nums):
                    i = i + 1;
                    j = i;
                    break;
            else:
                i = i + 1;
                j = i;
                break;

        while j < (len(nums) - 1):
            if nums[j] != nums[j+1]:
                nums[i]=nums[j+1];
                i = i + 1;
            j = j + 1;

        return i;
