class Solution {
    boolean debug = false;
    <T> void log(T v) {
        if(debug)
            System.out.println(v);
    }
    public int numWays(int n, int k) {
        int w = 1;
        int [] ways = new int[n];
        if(n==1){
            return k;
        }
        ways[0] = k;
        w = k*k;
        if(n==2) {
            return w;
        }
        ways[1] = w;
        w = w*k - k;
        if(n==3) {
            return w;
        }
        ways[2] = w;
        for(int i=3;i<n;i++){
            // ways[i] = k*ways[i-1] - ways[i-3]  * (k-1);
            ways[i] = k * (ways[i-1] -  ways[i-3]) + ways[i-3];
            // log("i="+i+"  pways_illegal=" + pways_illegal);
        }
        // log(Arrays.toString(ways));
        return ways[n-1];
    }
}