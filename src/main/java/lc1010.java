public class lc1010 {
    public int numPairsDivisibleBy60(int[] time) {
        int[] counts = new int[60];
        for (int i = 0; i < 60; i++) {
            counts[i] = 0;
        }
        for (int i = 0; i < time.length; i++) {
            int modulus = time[i] % 60;
            counts[modulus]++;
        }
        int accu = 0;
        for (int i = 0; i < 60; i++) {
            int j = (60 - i) % 60;
            int c = counts[i];
            int d = counts[j];
            if (c == 0 || d == 0) continue;
            if (i == j)
                accu += c * (c - 1) / 2;
            else
                accu += c * d;
        }
        return accu;
    }
}
