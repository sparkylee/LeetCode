class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0;
        if len(nums) <= 2:
            return max(nums);
        if nums[0] >= nums[1]:
            return nums[0] + self.rob(nums[2:]);
        if   nums[1]>= (nums[0] + nums[2]):
            return nums[1] + self.rob(nums[3:]);
        return max(nums[0]+self.rob(nums[2:]),nums[1]+self.rob(nums[3:]));