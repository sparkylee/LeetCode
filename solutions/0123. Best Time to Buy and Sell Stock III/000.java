class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length<=1) return 0;
            int [] highs = new int[prices.length], lows= new int[prices.length],
                    leftTran = new int[prices.length], rightTran = new int[prices.length];

            for(int i=highs.length-1;i>=0;i--)
            {
                if(i+1>=highs.length || prices[i]>= highs[i+1])
                    highs[i]=prices[i];
                else
                    highs[i] = highs[i+1];
            }

            for(int i=0;i<lows.length;i++)
            {
                if(i-1<0||prices[i]<=lows[i-1])
                    lows[i]=prices[i];
                else
                    lows[i]=lows[i-1];
            }
            for(int i=prices.length-2;i>=0;i--)
                rightTran[i]=Math.max(highs[i+1]-prices[i],rightTran[i+1]);
            for(int i=1;i<prices.length;i++)
                leftTran[i]=Math.max(prices[i] - lows[i-1],leftTran[i-1]);

            int maxProfit = rightTran[0];
            for(int i=0;i<prices.length-1;i++)
                maxProfit = Math.max(maxProfit,leftTran[i]+rightTran[i+1]);
            return maxProfit;
    }
}