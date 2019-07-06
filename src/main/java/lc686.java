import java.util.ArrayList;
import java.util.List;

public class lc686 {

    private int[] shiftIndex(String s) {
        int[] shift = new int[s.length()];
        for (int i = 0; i < shift.length; i++)
            shift[i] = -1;
        for (int i = 0; i < s.length(); i++) {
            int j = i + 1;
            while (j < s.length()) {
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
        return -1;
    }

    public int repeatedStringMatch(String A, String B) {

        return 0;
    }
}
