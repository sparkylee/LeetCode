class Solution {
    public int maxCount(int m, int n, int[][] ops) {
         for(int [] op: ops)
        {
            m = Math.min(op[0], m);
            n = Math.min(op[1], n);
        }
        return m*n;
    }
}