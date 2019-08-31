package lc11xx;

public class lc1160 {
    private void reset(int[] CS) {
        for (int i = 0; i < 26; i++)
            CS[i] = 0;
    }

    private void copy(int[] CS, int[] CSC) {
        System.arraycopy(CS, 0, CSC, 0, 26);
    }

    public int countCharacters(String[] words, String chars) {
        int[] CS = new int[26];
        reset(CS);
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            CS[c - 'a']++;
        }
        int len = 0;
        int[] CSC = new int[26];
        for (int i = 0; i < words.length; i++) {
            copy(CS, CSC);
            boolean isGood = true;
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (CSC[c - 'a'] == 0) {
                    isGood = false;
                    break;
                }
                CSC[c - 'a']--;
            }
            if (isGood) {
                len += words[i].length();
            }
        }
        return len;
    }
}
