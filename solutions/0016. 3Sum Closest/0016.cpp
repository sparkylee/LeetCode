
class Solution {
	int oneCloset(vector<int>& nums, int i, int j, int target)
	{
		if (i + 1 >= j)
		{
			return (abs(target - nums[i]) < abs(target - nums[j]) ? nums[i]:nums[j]);
		}
		int k = (i + j) / 2;
		if (target >= nums[k])
			return oneCloset(nums, k, j, target);
		else
			return oneCloset(nums, i, k, target);
		return 0;
	}
public:
	int threeSumClosest(vector<int>& nums, int target) {
		std::sort(nums.begin(), nums.end());
		int vs = nums.size();

		int diff = std::abs(nums[0] + nums[1] + nums[2] - target);
		int sum = nums[0] + nums[1] + nums[2];
		for (int i = 0; i < vs - 2; i++)
		{
			int a = nums[i];
			if (i>0 && ( a == nums[i - 1]))
				continue;
			for (int j = i + 1; j < vs -1 ; j++)
			{
				int b = nums[j];
				if (a + b + nums[j + 1] - target >= diff)
					break;
				if (a + b + nums.back() - target <= -diff)
					continue;
				int c = this->oneCloset(nums, j + 1, vs - 1, (target - a - b));
				int sum1 = a + b + c;
				int diff1 = abs(sum1 - target);
				if (diff1 < diff)
				{
					diff = diff1;
					sum = sum1;
					//cout << a << " " << b << " " << c << endl;
				}
			}
		}
		return sum;
	}

};