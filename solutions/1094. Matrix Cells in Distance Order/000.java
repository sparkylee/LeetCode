class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
      int tl = r0 + c0, tr = r0 + (C - c0), bl = (R - r0) + c0, br = (R - r0) + (C - c0);
        int disMax = Math.max(Math.max(tl, tr), Math.max(bl, br));
        List<List<int[]>> results = new ArrayList<>();
        for (int i = 0; i <= disMax; i++)
            results.add(new ArrayList<>());
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dis = Math.abs(i - r0) + Math.abs(j - c0);
                results.get(dis).add(new int[]{i, j});
            }
        }
        int[][] ret = new int[R * C][];
        int m = 0;
        for (int k = 0; k <= disMax; k++) {
            List<int[]> list = results.get(k);
            for (int l = 0; l < list.size(); l++) {
                ret[m] = list.get(l);
                m++;
            }
        }
        return ret;
    }
}