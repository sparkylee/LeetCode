class Solution(object):
    def generateSubset(self,nums_list,nums_cnt,result_list, candidate,k):
        if k >= len(nums_list):
            result_list.append(list(candidate));
            return;
        self.generateSubset(nums_list, nums_cnt, result_list, candidate, k + 1);
        for i in range(nums_cnt[k]):
            candidate.append(nums_list[k]);
            self.generateSubset(nums_list,nums_cnt,result_list,candidate,k+1);
        del candidate[-nums_cnt[k]:];
        return;

    def subsetsWithDup(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        result_list =[]
        nums_list = [];
        nums_cnt  = [];
        for item in nums:
            inSet = False;
            for i in range(len(nums_list)):
                if nums_list[i] == item:
                    nums_cnt[i] += 1;
                    inSet = True;
                    break;
            if not inSet:
                nums_list.append(item);
                nums_cnt.append(1);
        candidate = []
        self.generateSubset(nums_list,nums_cnt,result_list, candidate,0);
        return result_list;