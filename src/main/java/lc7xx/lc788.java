package lc7xx;

public class lc788 {
    private boolean isGoodNumber(int x) {
        boolean diffFound = false;
        while (x > 0) {
            int b = x % 10;
            if (b == 3 || b == 4 || b == 7) {
                return false;
            }
            if (b == 2 || b == 5 || b == 6 || b == 9)
                diffFound = true;
            x = x / 10;
        }
        return diffFound;
    }

    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++)
            if (isGoodNumber(i))
                count++;
        return count;
    }
}
