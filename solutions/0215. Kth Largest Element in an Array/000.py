class Solution:

    def partition(self, nums, l, r):
        p =random.randint(l, r)
        nums[l], nums[p] = nums[p], nums[l]
        pivot = nums[l]
        i, j, k = l, l, r
        while j<=k:
            if nums[j] < pivot:
                nums[i], nums[j] = nums[j], nums[i]
                i+=1
                j+=1
            elif nums[j] > pivot:
                nums[k], nums[j] = nums[j], nums[k]
                k-=1
            else:
                j+=1

        return i, j-1
    def quick(self, nums, l, r, k):
        if l<=r:
            i, j = self.partition(nums, l, r)
            if k in range(i, j+1):
                return nums[k]
            elif k<i:
                return self.quick(nums, l, i-1, k)
            elif k>j:
                return self.quick(nums, j+1, r, k)
    
    def findKthLargest(self, nums: List[int], k: int) -> int:
        return self.quick(nums, 0, len(nums)-1, len(nums)-k)        