import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc686 {
    @Test
    public void t1() {
        int[] result = shiftIndex("abcax");
        int i = matchSubstring("axa", "abaxcaxadt");
        System.out.println(i);
        System.out.println("dfafsd");
    }

    private int[] shiftIndex(String s) {
        int[] shift = new int[s.length()];
        for (int i = 0; i < shift.length; i++)
            shift[i] = -1;
        for (int i = 1; i < s.length(); i++) {
            int j = 0;
            while ((j + i) < s.length()) {
                if (s.charAt(i + j) == s.charAt(j)) {
                    if (shift[i + j] == -1)
                        shift[i + j] = i;
                    j++;
                } else
                    break;
            }
        }
        return shift;
    }

    private int matchSubstring(String s, String t) {
        int[] shift = shiftIndex(s);
        int i = 0, j = 0;
        boolean isMatched = true;
        int next_i = 0;
        int next_j = 0;
        while (i + s.length() <= t.length()) {
            isMatched = true;
            i = next_i;
            j = next_j;
            for (; j < s.length(); j++) {
                if (s.charAt(j) != t.charAt(i + j)) {
                    if (j <= 2) {
                        next_i = i + 1;
                        next_j = 0;
                    } else {
                        int delta = shift[j - 1] == -1 ? j : shift[j];
                        next_i = i + delta;
                        next_j = j - delta;
                    }
                    isMatched = false;
                    break;
                }
            }
            if (isMatched)
                return i;
        }
        return -1;
    }

    public int repeatedStringMatch(String A, String B) {

        return 0;
    }
}
