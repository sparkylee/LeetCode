import org.junit.Test;

import java.util.*;

public class lc811 {
    private void add2Map(Map<String, Integer> d2c, String domain) {
        d2c.computeIfPresent(domain, (k, v) -> v = v + 1);
        d2c.putIfAbsent(domain, 1);
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> d2c = new HashMap<>();
        for (int i = 0; i < cpdomains.length; i++) {
            String[] domain = cpdomains[i].trim().split("\\s+");
            if (domain.length != 2) continue;
            add2Map(d2c, domain[1]);
            for (int j = 1; j < domain[1].length(); j++)
                if (domain[1].charAt(j) == '.')
                    add2Map(d2c, domain[1].substring(j + 1));
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
