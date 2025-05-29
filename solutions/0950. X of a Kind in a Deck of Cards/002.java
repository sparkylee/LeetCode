class Solution {
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
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int c : deck) {
            countMap.computeIfPresent(c, (k, v) -> v = v + 1);
            countMap.putIfAbsent(c, 1);
        }
        int[] counts = new int[countMap.size()];
        Iterator it = countMap.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>) it.next();
            counts[i] = pair.getValue();
            i++;
        }
        int g = counts[0];
        for (i = 0; i < counts.length; i++)
            g = gcd(g, counts[i]);
        return g >= 2;
    }
}