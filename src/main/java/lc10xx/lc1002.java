package lc10xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc1002 {
    @Test
    public void test() {
        String[] A = {"bella", "label", "roller"};
        commonChars(A);
    }

    private void reset(int[] cs) {
        preset(cs, 0);
    }

    private void init(int[] cs) {
        preset(cs, -1);
    }

    private void preset(int[] cs, int value) {
        for (int j = 0; j < 26; j++) {
            cs[j] = value;
        }
    }

    private void adjust(int[] src, int[] dsc) {
        for (int j = 0; j < 26; j++) {
            if (dsc[j] == -1)
                dsc[j] = src[j];
            else
                dsc[j] = Math.min(dsc[j], src[j]);
        }
    }

    private void str2cs(String str, int[] cs) {
        reset(cs);
        for (int j = 0; j < str.length(); j++) {
            int index = str.charAt(j) - 'a';
            cs[index] += 1;
        }
    }

    public List<String> commonChars(String[] A) {
        int[] cs = new int[26];
        int[] csPerWord = new int[26];
        init(cs);
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
