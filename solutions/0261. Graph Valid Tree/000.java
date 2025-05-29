class Solution {
    void visitTree(boolean [] visited, List<Set<Integer>> setList, int p) {
        visited[p] = true;
        Set<Integer> set = setList.get(p);
        for(int q: set) {
            if(!visited[q]) {
                visitTree(visited, setList, q);
            }
               
        }
        return;
    }
    public boolean validTree(int n, int[][] edges) {
        if(edges.length!=n-1){
            return false;
        }
        List<Set<Integer>> setList = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            setList.add(new HashSet<Integer>());
        }
        for(int i=0;i<edges.length;i++){
            int [] edge = edges[i];
            for(int j=0;j<2;j++){
                int p = edge[j];
                int q = edge[1-j];
                Set s = setList.get(p);
                s.add(q);
            }
        }
        boolean [] visited = new boolean[n];
        
        visitTree(visited, setList, 0);
        for(int i=0;i<n;i++){
            if(!visited[i])
                return false;
        }
        return true;
    }
}