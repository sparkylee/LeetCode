// Forward declaration of isBadVersion API.
bool isBadVersion(int version);

class Solution {
public:
    int firstBadVersion(int n) {
      	int i,j,k;
		i = 1;
		k = n;
		j = (i + k ) / 2;
		do
		{
			if (isBadVersion(j))
			{
				if (i == j)
					return j;				
				if (i == j - 1 && !isBadVersion(i))
					return j;
				k = j;
				j = k/2 + i/2;
			}
			else
			{
				if (j == k - 1)
				{
					return k;
				}
				i = j;				
				j = k/2 + (i+1)/2;
			}
		
			
		} while (true);
		return j;
    }
};