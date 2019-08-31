package lc9xx;

import org.junit.Test;

public class lc953 {
    @Test
    public void test() {
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(isAlienSorted(words, order));
    }

    int[] c2v = new int[26];

    private int getValue(String word, int k) {
        if (k >= word.length())
            return -1;
        return c2v[word.charAt(k) - 'a'];
    }

    private boolean isSorted(String word, String wordNext) {
        int maxLen = Math.max(word.length(), wordNext.length());
        for (int i = 0; i < maxLen; i++) {
            int v0 = getValue(word, i);
            int v1 = getValue(wordNext, i);
            if (v0 < v1) return true;
            if (v0 > v1) return false;
        }
        return true;
    }

    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < 26; i++) {
            char c = order.charAt(i);
            c2v[c - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++)
            if (!isSorted(words[i], words[i + 1])) return false;
        return true;
    }
}
