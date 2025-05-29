class Solution {
    public int bitwiseComplement(int N) {
        if (N == 0) return 1;
        int k = 0;
        int full = 0;
        while (true) {
            full = full | (1 << k);
            if ((full | N) == full)
                return full - N;
            k++;
        }
    }
}