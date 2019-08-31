package lc1xx;

import org.junit.Test;

import java.util.*;

public class lc187 {
    private int add(int v, char c) {
        v = v << 2;
        switch (c) {
            case 'C':
                v += 1;
                break;
            case 'G':
                v += 2;
                break;
            case 'T':
                v += 3;
                break;
            default:
        }
        v = v & 0xfffff;
        return v;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> results = new ArrayList<>();
        if (s.length() < 10) return results;
        int v = 0;
        for (int i = 0; i < 9; i++) {
            v = add(v, s.charAt(i));
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 9; i < s.length(); i++) {
            v = add(v, s.charAt(i));
            if (!map.containsKey(v)) map.put(v, 1);
            else {
                if (map.get(v) == 1) {
                    String sub = s.substring(i - 9, i + 1);
                    results.add(sub);
                }
                map.put(v, 2);
            }

        }
        return results;
    }

}
