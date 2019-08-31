package lc7xx;

import org.junit.Test;

public class lc717 {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        if (bits[len - 1] != 0) return false;
        if (len == 1) return true;
        if (len >= 2 && bits[len - 2] == 0) return true;

        // there exists single 1
        boolean a1 = false;
        for (int i = 0; i < len - 2; i++) {
            if (bits[i] == 0)
                a1 = false; // there is no single 1
            else
                a1 = !a1; // double 1 or single 1
        }
        // there exists single one, so ending with 10 is not allowed.
        return a1;

    }
}
