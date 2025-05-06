class Solution {
    int searchInsertAdv(vector<int>& nums, int start, int end, int target)
    {
        if(start > end)
            return start;
        int middle = (start+end)/2;
        if(nums[middle] == target)
            return middle;

        if(start == end)
        {
            if(nums[middle] >= target)
            {
                return middle;
            }
            else
            {
                return middle+1;
            }
        }

        if(nums[middle] > target)
        {
            end = middle;
        }
        else
        {
            start = middle;
            if(start+1==end)
                start++;
        }
        return searchInsertAdv(nums,start,end, target);
    }
public:
    int searchInsert(vector<int>& nums, int target) {
        int end = (int)(nums.size()) - 1;
        return searchInsertAdv(nums,0,end,target);
    }
};