import java.util.*;

public class lc884 {
    public String[] uncommonFromSentences(String A, String B) {
        String[] As = A.trim().split("\\s+");
        String[] Bs = B.trim().split("\\s+");
        Map<String, Integer> map = new HashMap<>();
        for (String s : As) {
            map.computeIfPresent(s, (k, v) -> v = v + 1);
            map.putIfAbsent(s, 1);
        }
        for (String s : Bs) {
            map.computeIfPresent(s, (k, v) -> v = v + 1);
            map.putIfAbsent(s, 1);
        }

        List<String> results = new ArrayList<>();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();
            if (pair.getValue() == 1) {
                results.add(pair.getKey());
            }
        }
        return results.toArray(new String[results.size()]);
    }
}
