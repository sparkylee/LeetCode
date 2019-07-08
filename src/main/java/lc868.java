public class lc868 {
    public int binaryGap(int N) {
        int distMax = 0;
        int last = -1;
        for (int i = 0; i < 31; i++) {
            int bit = (N >> i) & 0x1;
            if (bit == 0) continue;
            if (last != -1) {
                int dis = i - last;
                distMax = Math.max(distMax, dis);
            }
            last = i;
        }
        return distMax;
    }
}
