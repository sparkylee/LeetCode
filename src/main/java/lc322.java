import org.junit.Test;

public class lc322 {
    @Test
    public void test() {
        t(new int[]{1, 2, 5}, 11);
        t(new int[]{3, 2, 5}, 11);
        t(new int[]{3}, 4);
        t(new int[]{1}, 4);
    }

    private void t(int[] coins, int amount) {
        System.out.println(coinChange(coins, amount));
    }
    private int coinChange(int[] coins, int i, int amount) {
        if (amount == 0)
            return 0;
        if (i >= coins.length || amount < 0) return -1;
        int j = 0;
        int countBest = -1;
        while (true) {
            int extraction = coins[i] * j;
            int newAmount = amount - extraction;
            if (newAmount < 0) return countBest;
            int leftover = coinChange(coins, i + 1, newAmount);
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
        int count = coinChange(coins, 0, amount);
        return count;
    }

}
