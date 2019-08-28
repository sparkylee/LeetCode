import org.junit.Test;

public class lc322 {
    @Test
    public void test() {
        t(new int[]{1, 2, 5}, 11);
        t(new int[]{3, 2, 5}, 11);
        t(new int[]{3}, 4);
        t(new int[]{1}, 4);
        t(new int[]{58, 92, 387, 421, 194, 208, 231}, 7798);
    }

    private void t(int[] coins, int amount) {
        System.out.println(coinChange(coins, amount));
    }

    private int countMin = -1;

    private int coinChange(int[] coins, int i, int amount, int countPre) {
        if (amount == 0) {
            if (this.countMin == -1)
                this.countMin = countPre;
            else
                this.countMin = Math.min(countPre, this.countMin);
            return 0;
        }

        if (i >= coins.length || amount < 0) return -1;
        int j = 0;
        int countBest = -1;
        while (true) {
            int extraction = coins[i] * j;
            int newAmount = amount - extraction;
            if (newAmount < 0) return countBest;
            if (this.countMin >= 0 && countPre + j > this.countMin)
                return countBest;
            int leftover = coinChange(coins, i + 1, newAmount, countPre + j);
            if (leftover >= 0) {
                int count = leftover + j;
                if (countBest == -1)
                    countBest = count;
                else
                    countBest = Math.min(count, countBest);
            }
            if (coins[i] == 0) return countBest;
            j++;
        }
    }

    public int coinChange(int[] coins, int amount) {
        this.countMin = -1;
        int count = coinChange(coins, 0, amount, 0);
        return count;
    }

}
