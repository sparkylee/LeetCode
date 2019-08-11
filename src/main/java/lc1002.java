import java.util.ArrayList;
import java.util.List;

public class lc1002 {
    private void reset(int[] cs) {
        for (int j = 0; j < 26; j++) {
            cs[j] = 0;
        }
    }

    private void adjust(int[] src, int[] dsc) {
        for (int j = 0; j < 26; j++) {
            if (dsc[j] == 0)
                dsc[j] = src[j];
            else
                dsc[j] = Math.min(dsc[j], src[j]);
        }
    }

    private void str2cs(String str, int[] cs) {
        reset(cs);
        for (int j = 0; j < str.length(); j++) {
            cs[str.charAt(j) - 'a'] += 1;
        }
    }

    public List<String> commonChars(String[] A) {
        int[] cs = new int[26];
        int[] csPerWord = new int[26];
        reset(cs);
        reset(csPerWord);
        for (int i = 0; i < A.length; i++) {
            str2cs(A[i], csPerWord);
            adjust(csPerWord, cs);
        }
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            for (int j = 0; j < cs[i]; j++)
                results.add(Character.toString(((char) ('a' + i))));

        return results;
    }
}
