
class Solution {
	bool checkInNums(vector<int>& nums, int m, int n, int d)
	{				
		if (m > n)
			return false;
		int i = (m + n) / 2;
		if (nums[i] == d)
			return true;
		if (nums[i] > d)
		{
			return checkInNums(nums, m, i-1, d);
		}
		else
		{
			return checkInNums(nums, i+1, n, d);
		}

		return false;
	}
	void printS4(vector<vector<int>> & s4)
	{
		for (int i = 0; i < s4.size(); i++)
		{
			cout << s4[i][0] << " " << s4[i][1] 
				<< "  " << s4[i][2] 
				<< "  " << s4[i][3]<<endl;
		}
	}
public:
	vector<vector<int>> fourSum(vector<int>& nums, int target) {
		std::sort(nums.begin(), nums.end());
		vector<vector<int>> s4;
		int ns = (int)(nums.size());
		int a0=0,b0=0,c0=0;
		for (int i = 0; i < ns - 3; i++)
		{
			int a = nums[i];		
			if (i != 0 && a == a0)
			{			
				continue;
			}
			a0 = a;
			for (int j = i + 1; j < ns - 2; j++)
			{
				int b = nums[j];
				if (j!=i + 1 && b ==b0) 
				{
					continue;
				}
				b0 = b;
				for (int k = j + 1; k < ns - 1; k++)
				{
					int c = nums[k];
					if (k != j + 1 && c == c0)
					{
						continue;
					}
					c0 = c;
					int d = target - a - b - c;
					if (d < nums[k + 1])
						break;
					if (d > nums.back() )
						continue;
					if(checkInNums(nums, k + 1, ns - 1, d))
					{
						vector<int> ve;
						ve.push_back(a);
						ve.push_back(b);
						ve.push_back(c);
						ve.push_back(d);
						s4.push_back(ve);						
					}
				}
			}
		}
		//printS4(s4);
		return s4;
	}

};
