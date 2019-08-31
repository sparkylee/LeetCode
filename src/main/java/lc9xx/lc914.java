package lc9xx;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class lc914 {
    private int gcd(int x, int y) {
        int mx = Math.max(x, y);
        int mi = Math.min(x, y);
        while (true) {
            int r = mx % mi;
            if (r == 0) return mi;
            mx = mi;
            mi = r;
        }
    }

    public boolean hasGroupsSizeX(int[] deck) {
        int[] counts = new int[10000];
        for (int i = 0; i < 10000; i++)
            counts[i] = 0;
        for (int c : deck)
            counts[c]++;
        int k = 0;
        while (counts[k] == 0) k++;
        int g = counts[k];
        for (int i = k; i < counts.length; i++) {
            if (counts[i] == 0) continue;
            g = gcd(g, counts[i]);
            if (g < 2) return false;
        }
        return g >= 2;
    }
}
