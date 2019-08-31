package lc8xx;

import org.junit.Test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class lc804 {
    @Test
    public void test1() {
        uniqueMorseRepresentations(new String[]{"d"});
    }

    private void initBSList() {
        BitSet bits1 = new BitSet();
    }

    private BitSet c2bs(String str) {
        BitSet bs = new BitSet(str.length());
        String s;
        return null;
    }

    String[] c2s = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    private String c2sFunc(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append(c2s[word.charAt(i) - 'a']);
        }
        return sb.toString();
    }

    public int uniqueMorseRepresentations(String[] words) {

        Set<String> set = new HashSet<>();
        for (String w : words) {
            String snew = c2sFunc(w);
            set.add(snew);
        }
        return set.size();
    }
}
