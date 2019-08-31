package lc3xx;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class lc322 {
    @Test
    public void test() {
        long t1 = System.currentTimeMillis();
        t(new int[]{1, 2, 5}, 11);
        t(new int[]{3, 2, 5}, 11);
        t(new int[]{3}, 4);
        t(new int[]{1}, 4);
        t(new int[]{58, 92, 387, 421, 194, 208, 231}, 7798);
        t(new int[]{342, 268, 284, 65, 217, 461, 245, 249, 106}, 9278);
        t(new int[]{120, 6, 320, 300, 100, 192, 212, 89, 106, 461}, 8332);
        t(new int[]{496, 154, 300, 357, 327, 248, 201, 341, 145, 248, 316}, 6776);
        long t2 = System.currentTimeMillis();
        System.out.println("time used: " + (t2 - t1));
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

        if (i >= coins.length || i < 0 || amount < 0) return -1;
        if (coins[i] == 0) return -1;
        int j = amount / coins[i];
        if (i == 0) {
            int modulus = amount % coins[i];
            if (modulus != 0) return -1;
            if (this.countMin == -1)
                this.countMin = countPre + j;
            else
                this.countMin = Math.min(countPre + j, this.countMin);
            return j;
        }
        int countBest = -1;
        int extraction = 0;
        while (j >= 0) {
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
        int count = coinChange(coins, coins.length - 1, amount, 0);

        return count;
    }

}
