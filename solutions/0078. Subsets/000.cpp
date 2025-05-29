class Solution {
    vector<vector<int>> vvi;
    void searchSubsets(vector<int>& nums,vector<int> & sel, int k)
    {
        if(k==nums.size())
        {
            vvi.push_back(sel);            
            return;
        }
        searchSubsets(nums,sel,k+1);
        sel.push_back(nums[k]);
        searchSubsets(nums,sel,k+1);
        sel.pop_back();
        return;
        
    }
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<int> sel;
        searchSubsets(nums,sel,0);
        return vvi;
    }
};