import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class lc693 {
    @Test
    public void test() {
        t(5);
    }

    private void t(int n) {
        System.out.println(hasAlternatingBits(n));
    }

    public boolean hasAlternatingBits(int n) {
        int bit = 0;
        boolean startChecking = false;
        for (int i = 31; i >= 0; i--) {
            int bitNew = (n >> i) & 0x1;
            if (startChecking == false) {
                if (bitNew == 1)
                    startChecking = true;
            } else {
                if (bit == bitNew)
                    return false;
            }
            bit = bitNew;
        }
        return true;
    }
}
