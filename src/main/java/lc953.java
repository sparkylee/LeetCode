import org.junit.Test;

public class lc953 {
    int[] c2v = new int[26];

    private int getValue(String word, int k) {
        if (k >= word.length())
            return -1;
        return c2v[word.charAt(k) - 'a'];
    }

    public boolean isAlienSorted(String[] words, String order) {

        for (int i = 0; i < 26; i++) {
            char c = order.charAt(i);
            c2v[c - 'a'] = i;
        }
        int maxLen = 0;
        for (int i = 0; i < words.length; i++)
            maxLen = Math.max(maxLen, words[i].length());
        for (int i = 0; i < maxLen; i++) {
            int v = getValue(words[0], i);
            for (int j = 1; j < words.length; j++) {
                int v1 = getValue(words[j], i);
                if (v1 < v)
                    return false;
                v = v1;
            }
            i++;
        }
        return true;
    }
}
