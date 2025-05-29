class Solution {
    int getNextProfitFrom(int [] profits, int j) {
        if(j<0||j>=profits.length) 
            return 0;
        return profits[j];
    }
    public int maxProfit(int[] prices) {
        int [] profits = new int[prices.length];
        profits[profits.length-1] = 0;
        for(int i=profits.length-2;i>=0;i--) {
            int profits_not_buy = getNextProfitFrom(profits, i+1);
            int profit = profits_not_buy;
            for(int j=i+1;j<prices.length;j++) {
                int profit_for_transaction = prices[j] - prices[i];
                int profit_to_buy = profit_for_transaction + getNextProfitFrom(profits, j+2);
                profit = Math.max(profit, profit_to_buy);
            }
            profits[i] = profit;
        }
        // System.out.println(Arrays.toString(profits));
        return profits[0];
    }
}