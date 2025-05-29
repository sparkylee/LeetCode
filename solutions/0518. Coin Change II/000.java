class Solution {
   private int coinChange(int[] coins, int i, int amount, int [] [] checked) {
        if (amount == 0) return 1;
        if (i >= coins.length || i < 0 || amount < 0) return 0;
        if(checked[i][amount-1]>=0) return checked[i][amount-1];
        if (coins[i] == 0) return 0;
        int count = 0;
        if (i == coins.length-1){
            count = amount % coins[i] == 0 ? 1:0;
            checked[i][amount-1] = count;
            return count;
        }
        int j=0;

        while (true) {
            int newAmount = amount - coins[i] * j;
            if (newAmount < 0) {
                checked[i][amount-1] = count;
                return count;
            }
            int countNew = coinChange(coins, i + 1, newAmount, checked);
            count += countNew;
            j++;
        }
    }

    public int change(int amount, int[] coins) {
        if(amount==0) return 1;
        int [][] checked = new int[coins.length][amount];
        for(int i=0;i<coins.length;i++) {
            for(int j=0;j<amount;j++){
                checked[i][j] = -1;
            }
        }
        int count = coinChange(coins, 0, amount, checked);
        return count;
    }
}