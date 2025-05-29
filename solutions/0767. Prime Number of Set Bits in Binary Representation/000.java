class Solution {
   private int countBit(int a) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int b = (a >> i) & 0x1;
            if (b == 1)
                count++;
        }
        return count;
    }

    public int countPrimeSetBits(int L, int R) {
        Set<Integer> set = new HashSet<>();
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int prime : primes) {
            set.add(prime);
        }
        int count = 0;
        for (int a = L; a <= R; a++) {
            int bits = countBit(a);
            if (set.contains(bits))
                count++;
        }
        return count;
    }
}