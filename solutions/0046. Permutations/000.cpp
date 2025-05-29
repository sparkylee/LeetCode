class Solution {
    vector<vector<int>> vve;
    void permuteRecursive(vector<int>& nums,int k)
    {
        if(k == nums.size())
        {
            vve.push_back(nums);
            return;
        }
        for(int i = k; i < nums.size(); i++)
        {
            int tmp = nums[k];
            nums[k] = nums[i];
            nums[i] = tmp;
            permuteRecursive(nums,k+1);
            nums[i] = nums[k];
            nums[k] = tmp;
            
        }
        return;
    }
public:
    vector<vector<int>> permute(vector<int>& nums) {
        permuteRecursive(nums,0);
        return vve;
    }
};