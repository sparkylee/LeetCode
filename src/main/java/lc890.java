import java.util.ArrayList;
import java.util.List;

public class lc890 {
    private void reset(int[] mapping) {
        for (int i = 0; i < mapping.length; i++) {
            mapping[i] = 0;
        }
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] mappP2W = new int[256];
        int[] mappW2P = new int[256];
        List<String> results = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            reset(mappP2W);
            reset(mappW2P);
            boolean matched = true;
            for (int j = 0; j < pattern.length(); j++) {
                char c = pattern.charAt(j);
                char d = words[i].charAt(j);
                if (mappP2W[c] == 0)
                    mappP2W[c] = d;
                else if (mappP2W[c] != d) {
                    matched = false;
                    break;
                }
                if (mappW2P[d] == 0)
                    mappW2P[d] = c;
                else if (mappW2P[d] != c) {
                    matched = false;
                    break;
                }
            }
            if (matched) results.add(words[i]);
        }
        return results;
    }
}
