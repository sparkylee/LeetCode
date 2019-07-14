import org.junit.Test;

import java.util.*;

public class lc811 {
    private void add2Map(Map<String, Integer> d2c, String domain, int count) {
        d2c.computeIfPresent(domain, (k, v) -> v = v + count);
        d2c.putIfAbsent(domain, count);
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> d2c = new HashMap<>();
        for (int i = 0; i < cpdomains.length; i++) {
            String[] domain = cpdomains[i].trim().split("\\s+");
            if (domain.length != 2) continue;
            int count = Integer.valueOf(domain[0]);
            add2Map(d2c, domain[1], count);
            for (int j = 1; j < domain[1].length(); j++)
                if (domain[1].charAt(j) == '.')
                    add2Map(d2c, domain[1].substring(j + 1), count);
        }
        List<String> results = new ArrayList<>();
        Iterator it = d2c.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
            results.add(pair.getValue() + " " + pair.getKey());
        }
        return results;
    }
}
