import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc392 {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) return true;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean charMatched = false;
            while (j < t.length()) {
                char d = t.charAt(j);
                j++;
                if (d == c) {
                    charMatched = true;
                    break;
                }
            }
            if (!charMatched)
                return false;
        }
        return true;
    }
}
