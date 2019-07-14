import org.junit.Test;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class lc806 {
    public int[] numberOfLines(int[] widths, String S) {
        int[] results = new int[2];
        results[0] = 1;
        int line = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            int index = c - 'a';
            int newLen = line + widths[index];
            if (newLen <= 100)
                line = newLen;
            else {
                results[0]++;
                line = widths[index];
            }
        }
        results[1] = line;
        return results;
    }
}
