class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Set<Integer>> stack = new ArrayList<>();
        Set<Integer> set_full = new HashSet<>();
        for (int i=0;i<edges.length;i++) {
            Set<Integer> set = new HashSet<>();
            set.add(edges[i][0]);
            set.add(edges[i][1]);
            stack.add(set);
            set_full.add(edges[i][0]);
            set_full.add(edges[i][1]);
        }
        int diff = n - set_full.size();
        int count = 0;
        for (int i=stack.size()-1;i>=0;i--) {
            Set<Integer> set = stack.get(i);
            boolean merged = false;
            for(int j = i - 1;j >= 0; j--) {
                Set<Integer> setj = stack.get(j);
                if(!Collections.disjoint(set, setj)) {
                    setj.addAll(set);
                    merged = true;
                    break;
                }
            }
            count += merged?0:1;
        }
        return count + diff;
    }
}