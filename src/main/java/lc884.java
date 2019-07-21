import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc884 {
    public String[] uncommonFromSentences(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0)
            return new String[]{};
        String[] As = A.trim().split("\\s+");
        String[] Bs = B.trim().split("\\s+");
        Set<String> Aset = new HashSet<>();
        Set<String> Bset = new HashSet<>();
        for (String s : As) {
            Aset.add(s);
        }
        for (String s : Bs) {
            Bset.add(s);
        }
        List<String> results = new ArrayList<>();
        for (String s : Aset) {
            if (!Bset.contains(s)) {
                results.add(s);
            }
        }
        for (String s : Bset) {
            if (!Aset.contains(s)) {
                results.add(s);
            }
        }
        return results.toArray(new String[results.size()]);
    }
}
