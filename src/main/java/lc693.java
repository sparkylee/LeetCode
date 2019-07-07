import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class lc693 {
    public boolean hasAlternatingBits(int n) {
        int bit = n & 0x1;
        for (int i = 1; i < 32; i++) {
            int bitNew = (n >> i) & 0x1;
            if (bit == bitNew) return false;
            bit = bitNew;
        }
        return true;
    }
}
