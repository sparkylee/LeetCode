class Solution {
public:
    int maxProfit(vector<int>& prices) {
       int bp = 0, pp = 1;
		bp = 0;
		pp = 0;		
		if (prices.empty())
			return 0;	
		int profit = 0;
		while ((bp < prices.size() - 1) && (pp < prices.size()))
		{
			bp = searchBottom(prices, pp);
			pp = searchPeak(prices, bp);
			profit += prices[pp] - prices[bp];
		}
		return profit; 
    }
private:	
	int searchPeak(vector<int>& prices,int start)
	{
		while ((start + 1 < prices.size()) && (prices[start] <= prices[start + 1]))
			start++;
		return start;
	}
	int searchBottom(vector<int>& prices, int start)
	{
		while ((start + 1 < prices.size()) && (prices[start] >= prices[start + 1]))
			start++;
		return start;
	}    
};