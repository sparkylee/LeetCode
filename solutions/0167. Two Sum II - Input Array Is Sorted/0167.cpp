class Solution {
public:
  int searchIndex(vector<int> & numbers, int start, int end, int target, int eq) //Eq = 1 exact value; Eq = 0 nearest value
	{
		int i = 0, i1 = 0;
		i = (start + end) / 2;
		i1 = i;
		if (numbers[start] > target)
			return -1;
		while (true)
		{
			//////////////////////////
			if ((eq == 1) && (numbers[i] == target))
				return i;
			if (eq == 0)
			{
				if (numbers[i] == target)
					return i;
				if (numbers[i] < target)
				{
					if (((i + 1)>end) || (numbers[i + 1] > target))
						return i;
				}
			}
			//////////////////////////
			if (start == end)
				return -1;
			//////////////////////////
			if (numbers[i] < target)
				start = i;
			else if (numbers[i] > target)
				end = i;
			//////////////////////////
			i1 = i;
			i = (start + end) / 2;
			if (i == i1)
			{
				i++;
				start = i;
			}

		};
		return -1;

	}
	vector<int> twoSum(vector<int>& numbers, int target) {
		vector<int> ve;
		int end = searchIndex(numbers, 0, numbers.size() - 1, target - numbers[0], 0);
		int middle = searchIndex(numbers, 0, end, target / 2, 0);
		if (middle == end)
			middle--;
		int k0, k1, l0, l1;

		if ((middle - 0) < (end - middle))
		{
			k0 = 0;
			k1 = middle;
			l0 = middle+1;
			l1 = end;
		}
		else
		{
			k0 = middle+1;
			k1 = end;
			l0 = 0;
			l1 = middle;
		}
		for (int i = k0; i <= k1; i++)
		{
			int ohs = target - numbers[i];
			int j = searchIndex(numbers, l0, l1, ohs, 1);
			if (j != -1 )
			{
				ve.push_back(((i < j) ? i : j) + 1);
				ve.push_back(((i < j) ? j : i) + 1);
				return ve;
			}
		}
		return ve;
	}
};