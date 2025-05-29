
class Solution {
	int SUM = 0;
	int getCIndex(vector<int>& nums, int m, int n,  int c)
	{		
		int i = (m + n) / 2;
		if (nums[i] == c)
			return i;
		if (m >= n)
			return -1;

		if (nums[i] > c)
		{		
			return getCIndex(nums, m, i, c);
		}
		else
		{						
			return getCIndex(nums, i+1, n, c);
		}
		
		return -1;
	}
	bool isDuplicate(vector<vector<int>> & s3, vector<int> & ve)
	{
		for (int i = s3.size() - 1; i >= 0; i--)
		{
			if (s3[i][0] < ve[0])
				return false;
			if (s3[i][1] == ve[1])
				return true;			
		}
		return false;
	}
public:
	void printS3(vector<vector<int>> & s3)
	{
		for (int i = 0; i < s3.size(); i++)
		{
			cout << s3[i][0] << " " << s3[i][1] << "  " << s3[i][2] << endl;
		}
	}
	vector<vector<int>> threeSum(vector<int>& nums) {
		this->SUM = 0;
		std::sort(nums.begin(), nums.end());
		vector<vector<int>> s3;
		for (int i = 0; i <= (int)(nums.size()) - 3; i++)
		{
			int a = nums[i];
			if (a * 3 > this->SUM)
				break;
		//	if (a == -1)
		//		cout << "hello" << endl;
			if ( ((i-1)>= 0) && (a == nums[i - 1])) // 
			{
				continue;//check redundancy of aaa,aab
			}
			for (int j = i + 1; j <= (int)(nums.size()) - 2; j++)
			{
				int b = nums[j];					
				int c = this->SUM - a - b;
				if (c < nums[j + 1])
					break;
				if (nums.back() < c)
					continue;
				if ((j - 1) >= 0 && b == nums[j - 1] && i < (j -1)) // 
					continue;//check redundancy of abb
				int k = getCIndex(nums, j, (int)(nums.size()) -1, c);
				if (k == -1)
					continue;
				else
				{
					vector<int> ve;
					ve.push_back(a);
					ve.push_back(b);
					ve.push_back(c);					
					s3.push_back(ve);
				//	cout << ve[0] << " " << ve[1] << " " << ve[2] << endl;
				}

			}
			
		}
		return s3;
	}
};