class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        vector<int> result;
		int lv = 1;
		for (int i = 0; i < nums.size(); i++)
		{
			result.push_back(lv);
			lv = nums[i] * lv;
		}
		int rv = 1;
		for (int i = nums.size() - 1; i >= 0; i--)
		{
			lv = result[i];
			result[i] = rv * lv;
			rv = nums[i] * rv;
		}
		return result;
    }
};