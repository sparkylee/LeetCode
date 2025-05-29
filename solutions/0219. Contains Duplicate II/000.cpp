class Solution {
public:
 int searchNextTurn(vector<int> & nums, int i)
  {
	  int N = nums.size();
	  if (i >= (N-1))
		  return -1;
	  int j = i + 1;
	  int delta0 = nums[j] - nums[i];
	  if (delta0 == 0)
		  return 0;
	  j++;
	  while (j < N)
	  {		  
		  int delta1 = nums[j] - nums[j-1];
		  if (delta1 == 0)
			  return 0;
		  if ((delta0 < 0 && delta1 > 0) || (delta0 > 0 && delta1 < 0))
			  return (j - 1);
		  j++;
	  }
	  return -1;

  }
  bool containsNearbyDuplicate(vector<int>& nums, int k) {
	  int tp = 0;
	  if ((k <= 0) || (nums.size() == 0) )
		  return false;
	  while (true)
	  {
		  tp = searchNextTurn(nums, tp);
		  if (tp == 0)
			  return true;
		  if (tp == -1)
			  return false;
		  int i = (tp - k + 1);
		  i = (i > 0) ? i:0;
		  for (; i <= tp; i++)
		  {
			  int N = nums.size();
			  for (int j = tp + 1; (j <= (i + k)) && j < N; j++)
			  {
				  if (nums[i] == nums[j])
					  return true;
			  }
		  }
		  
	  }
	  return false;
  }
};