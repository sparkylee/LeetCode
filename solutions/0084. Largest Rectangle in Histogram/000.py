class Solution(object):
    def largestRectangleArea(self, heights):
        """
        :type heights: List[int]
        :rtype: int
        """
        if not heights:
            return 0;
        i,j =0,0;
        max_area = heights[0];
        area = heights[0];
        n  = len(heights);
        next_j_list = [0]*n;
        for k in reversed(range(n)):
            if k == n - 1 or heights[k] > heights[k+1]:
                mountain_j = k;
            next_j_list[k] = mountain_j;
        next_i_list = [0];
        for k in range(1,n):
            if heights[k] > heights[k-1]:
                next_i_list.append(k);

        for i in next_i_list:
            min_h = heights[i];
            j = i;
            while True:
                min_h = min(heights[j], min_h);
                j = next_j_list[j];
                area = min_h * (j-i+1);
                max_area = max(area,max_area);
                if j == n -1:
                    break;
                j += 1;
        return max_area;