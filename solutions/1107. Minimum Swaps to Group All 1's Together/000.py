class Solution:
    def minSwaps(self, data: List[int]) -> int:
        sm = sum(data)
        tmp = sm-sum(data[:sm])
        ans = tmp
        for i in range(sm, len(data)):
            tmp = tmp-data[i]+data[i-sm]
            ans = min(ans, tmp)
        return ans       