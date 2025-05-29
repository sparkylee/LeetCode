class Solution {
public:
    int countPrimes(int n) {
     	if (n == 0 || n == 1 || n ==2)
			return 0;		
		vector<int> primes;
		primes.push_back(2);
		int k = 3;
		int j = 0;
		while (k < n)
		{
			bool isPrime = true;
			while (primes[j] * primes[j] <= k)
			{
				j++;
			}
			for (int i = 0; i < j; i++)
			{
				if (k % primes[i] == 0)
				{
					isPrime = false;
					break;
				}				
			}
			if (isPrime)
			{
				primes.push_back(k);
			}
			
			k += 2;
		}
		//for (int i = 0; i < primes.size(); i++)
		//{
		//	cout << primes[i] << endl;
		//}
		return primes.size();
    }
};