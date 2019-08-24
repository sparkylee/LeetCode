import java.util.ArrayList;
import java.util.List;

public class lc890 {
    private void reset(int[] mapping) {
        for (int i = 0; i < mapping.length; i++) {
            mapping[i] = 0;
        }
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] mapping = new int[256];
        List<String> results = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            reset(mapping);
            boolean matched = true;
            for (int j = 0; j < pattern.length(); j++) {
                char c = pattern.charAt(j);
                char d = words[i].charAt(j);
                if (mapping[c] == 0)
                    mapping[c] = d;
                else if (mapping[c] != d) {
                    matched = false;
                    break;
                }
            }
            if (matched) results.add(words[i]);
        }
        return results;
    }
}
