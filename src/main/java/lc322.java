import org.junit.Test;

public class lc322 {
    private int coinChange(int[] coins, int i, int amount) {
        if (amount == 0)
            return 0;
        if (i >= coins.length) return -1;
        int j = 1;
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
                    countBest = Math.max(count, countBest);
            }
            j++;
        }
    }

    public int coinChange(int[] coins, int amount) {
        int count = coinChange(coins, 0, amount);
        return count;
    }

}
