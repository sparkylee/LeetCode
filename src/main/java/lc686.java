import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc686 {
    @Test
    public void t1() {
        int[] result = shiftIndex("abcax");
        System.out.println("dfafsd");
    }

    private int[] shiftIndex(String s) {
        int[] shift = new int[s.length()];
        for (int i = 0; i < shift.length; i++)
            shift[i] = -1;
        for (int i = 1; i < s.length(); i++) {
            int j = 0;
            while ((j + i) < s.length()) {
                if (s.charAt(j + i) == s.charAt(j)) {
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
        return -1;
    }

    public int repeatedStringMatch(String A, String B) {

        return 0;
    }
}
