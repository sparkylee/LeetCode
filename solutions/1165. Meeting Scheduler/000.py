class Solution:
    def minAvailableDuration(self, slots1: List[List[int]], slots2: List[List[int]], duration: int) -> List[int]:
        l1, l2 = len(slots1), len(slots2)
        i, j = 0, 0
        slots1.sort(key=lambda x: x[0])
        slots2.sort(key=lambda x:x[0])
        while i < l1 and j < l2:
            l, r = max(slots1[i][0], slots2[j][0]), min(slots1[i][1], slots2[j][1])
            if r>=l:
                if r-l>=duration:
                    return [l, l+duration]

            if slots1[i][1]<slots2[j][1]:
                i+=1
            else:
                j+=1
        return []