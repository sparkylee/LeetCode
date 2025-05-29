class Solution {
      private Comparator<int[]> FruitNameComparator
            = new Comparator<int[]>() {
        public int compare(int[] fruit1, int[] fruit2) {
            int abs1 = Math.abs(fruit1[0] - fruit1[1]);
            int abs2 = Math.abs(fruit2[0] - fruit2[1]);
            return abs2 - abs1;
        }
    };

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, FruitNameComparator);
        int[] counts = {0, 0};
        int N = costs.length / 2;
        int costAll = 0;
        for (int i = 0; i < costs.length; i++) {
            if (counts[0] >= N) {
                counts[1]++;
                costAll += costs[i][1];
                continue;
            }
            if (counts[1] >= N) {
                counts[0]++;
                costAll += costs[i][0];
                continue;
            }
            if (costs[i][0] < costs[i][1]) {
                counts[0]++;
                costAll += costs[i][0];
            } else {
                counts[1]++;
                costAll += costs[i][1];
            }

        }
        return costAll;
    }
}