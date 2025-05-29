class Solution {
    private int countMin = -1;

    private int coinChange(int[] coins, int i, int amount, int countPre) {
        if (amount == 0) {
            if (this.countMin == -1)
                this.countMin = countPre;
            else
                this.countMin = Math.min(countPre, this.countMin);
            return 0;
        }

        if (i >= coins.length || i<0 || amount < 0) return -1;
        if (coins[i] == 0) return -1;
        int j = amount / coins[i];
        if (i == 0) {
            int modulus = amount % coins[i];
            if (modulus != 0) return -1;
            if (this.countMin == -1)
                this.countMin = countPre+j;
            else
                this.countMin = Math.min(countPre+j, this.countMin);
            return j;
        }
        int countBest = -1;
        int extraction = 0;
        while (j>=0) {
            extraction = coins[i] * j;
            int newAmount = amount - extraction;
            if (newAmount < 0) return countBest;
            if (this.countMin >= 0 && countPre + j > this.countMin)
                return countBest;
            if (countBest >= 0 && j >= countBest) return countBest;
            int leftover = coinChange(coins, i - 1, newAmount, countPre + j);
            if (leftover >= 0) {
                int count = leftover + j;
                if (countBest == -1)
                    countBest = count;
                else
                    countBest = Math.min(count, countBest);
            }
            if (coins[i] == 0) return countBest;
            j--;
        }
        return countBest;
    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        this.countMin = -1;
        int count = coinChange(coins, coins.length-1, amount, 0);

        return count;
    }
}