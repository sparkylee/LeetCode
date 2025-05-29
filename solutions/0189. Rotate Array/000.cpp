class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        int n = nums.size();
		if (n == 0)
			return;
		vector<int> tmp;
		k = k % n;
		for (int i = n - k; i < n; i++)
		{
			tmp.push_back(nums[i]);
		}
		for (int j = n - k - 1; j >= 0; j--)
		{
			nums[j+k] = nums[j];
		}
		for (int j = 0; j < k; j++)
		{
			nums[j] = tmp[j];
		}
		return;
    }
};