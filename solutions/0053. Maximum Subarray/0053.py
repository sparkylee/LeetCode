
class Solution(object):
    def addItem(self,nums_s,item):
        if not nums_s and item <= 0:
            return;
        if item == 0:
            return;
        if item < 0:
            if nums_s and nums_s[-1] < 0:
                nums_s[-1] += item;
            else:
                nums_s.append(item);
            return;
        if len(nums_s) >= 2 and nums_s[-2] >= -nums_s[-1] and item >= -nums_s[-1]:
            nums_s[-2] = nums_s[-2] + nums_s[-1] + item;
            del nums_s[-1];
            return;
        nums_s.append(item);
        return;
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums_s = [];
        s = 0;
        preItem  = None;
        for i in range(len(nums)):
            if i > 0 and ( (nums[i] < 0 and nums[i-1] >=0) or (nums[i] >= 0 and nums[i-1] < 0) ):
                self.addItem(nums_s,s);
                s = nums[i];
            else:
                s += nums[i];
        if s > 0:
            self.addItem(nums_s, s);
        i = 0;
        n = len(nums_s);
        #print(n)
        max_area = -1;
        for i in range(n):
            if nums_s[i] < 0:
                continue;
            area = 0;
            for j in range(i,n):
                area += nums_s[j];
                if area > max_area:
                    max_area = area;
        if max_area < 0:
            max_area  = nums[0];
            for item in nums:
                 if item > max_area:
                     max_area = item;
        return max_area;