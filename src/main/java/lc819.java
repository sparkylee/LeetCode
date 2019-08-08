import java.util.*;

public class lc819 {
    boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    char toLowercase(char c) {
        if (c >= 'A' && c <= 'Z')
            c = (char) (c - 'A');
        return c;
    }

    Map<String, Integer> paragraph2Map(String paragraph, Set<String> bannedSet) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < paragraph.length(); i++) {
            char c = paragraph.charAt(i);
            if (isLetter(c)) {
                sb.append(toLowercase(c));
                continue;
            }
            if (sb.length() == 0) continue;
            String x = sb.toString();
            if (bannedSet.contains(x)) continue;
            wordMap.computeIfPresent(x, (k, v) -> v = v + 1);
            wordMap.putIfAbsent(x, 1);
            sb.setLength(0);
            sb.trimToSize();
        }
        return wordMap;
    }

    Set<String> banned2Set(String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        for (int i = 0; i < banned.length; i++) {
            bannedSet.add(banned[i]);
        }
        return bannedSet;
    }

    String getFrequentWord(Map<String, Integer> wordMap) {
        Iterator it = wordMap.entrySet().iterator();
        String X = null;
        int count = 0;
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry) it.next();
            if (pair.getValue() >= count) {
                X = pair.getKey();
            }
        }
        return X;
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = banned2Set(banned);
        Map<String, Integer> wordMap = paragraph2Map(paragraph, bannedSet);
        return getFrequentWord(wordMap);
    }
}
