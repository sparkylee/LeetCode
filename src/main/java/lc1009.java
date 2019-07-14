import java.util.Arrays;

public class lc1009 {
    public int bitwiseComplement(int N) {
        if (N == 0) return 1;
        int k = 0;
        while (true) {
            int full = (1 << k);
            if ((full | N) == full)
                return full - N;
            k++;
        }
    }
}
