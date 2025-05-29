class Solution {
    boolean debug = false;
    <T> void log(T v) {
        if(debug)
            System.out.println(v);
    }
    public int numWays(int n, int k) {
        
        int [] ways = new int[n];
        if(n==1){
            return k;
        }
        if(n==2) {
            return k*k;
        }
        if(n==3) {
            return k*k*k - k;
        }
        ways[0] = k;
        ways[1] = ways[0] *k;
        ways[2] = k*k*k - k;
        for(int i=3;i<n;i++){
            int pways_total = k*ways[i-1];
            int pways_illegal = (i-3>=0? ways[i-3] : 1) * (k-1);
            int pways_legal = pways_total - pways_illegal;
            ways[i] = pways_legal;
            log("i="+i+"  pways_illegal=" + pways_illegal);
        }
        log(Arrays.toString(ways));
        return ways[n-1];
    }
}