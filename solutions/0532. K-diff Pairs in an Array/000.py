class Solution:
    def findPairs(self, nums: List[int], k: int) -> int:
        count = Counter(nums)
        ans = 0
        if k==0:
            for i,v in count.items():
                if v>=2: ans+=1
        else:
            for i in count:
                if i+k in count: ans+=1
        return ans