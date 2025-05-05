class Solution {
    public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> indice;
        for (int i = 0; i < nums.size(); i++)
        {
            for (int j = i + 1; j < nums.size(); j++)
            {
                if (nums[i] + nums[j] == target)
                {
                    indice.push_back(i);
                    indice.push_back(j);
                    return indice;
                }
            }
        }
        return indice;
    }
}