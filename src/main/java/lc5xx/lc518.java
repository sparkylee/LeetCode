package lc5xx;

import org.junit.Test;

public class lc518 {
    @Test
    public void test() {
        long t1 = System.currentTimeMillis();
        t(new int[]{1, 2, 5}, 5);
        t(new int[]{2}, 3);
        t(new int[]{10}, 10);
        t(new int[]{10}, 0);
        t(new int[]{3, 5, 7, 8, 9, 10, 11}, 500);
        t(new int[]{11, 10, 9, 8, 7, 5, 3}, 500);
       /* t(new int[]{3, 2, 5}, 11);
        t(new int[]{3}, 4);
        t(new int[]{1}, 4);
        t(new int[]{58, 92, 387, 421, 194, 208, 231}, 7798);
        t(new int[]{342, 268, 284, 65, 217, 461, 245, 249, 106}, 9278);
        t(new int[]{120, 6, 320, 300, 100, 192, 212, 89, 106, 461}, 8332);
        t(new int[]{496, 154, 300, 357, 327, 248, 201, 341, 145, 248, 316}, 6776);*/
        long t2 = System.currentTimeMillis();
        System.out.println("time used: " + (t2 - t1));
    }

    private void t(int[] coins, int amount) {
        System.out.println(change(amount, coins));
    }

    private int coinChange(int[] coins, int i, int amount, int[][] checked) {
        if (amount == 0) return 1;
        if (i >= coins.length || i < 0 || amount < 0) return 0;
        if (checked[i][amount - 1] >= 0) return checked[i][amount - 1];
        if (coins[i] == 0) return 0;
        int count = 0;
        if (i == coins.length - 1) {
            count = amount % coins[i] == 0 ? 1 : 0;
            checked[i][amount - 1] = count;
            return count;
        }
        int j = 0;

        while (true) {
            int newAmount = amount - coins[i] * j;
            if (newAmount < 0) {
                checked[i][amount - 1] = count;
                return count;
            }
            int countNew = coinChange(coins, i + 1, newAmount, checked);
            count += countNew;
            j++;
        }
    }

    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        int[][] checked = new int[coins.length][amount];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount; j++) {
                checked[i][j] = -1;
            }
        }
        int count = coinChange(coins, 0, amount, checked);
        return count;
    }

}
