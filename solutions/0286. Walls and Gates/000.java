class Solution {
    private static final int INF = 2147483647 ;
    int [][] rooms;
    int m,n;
    void wallsAndGates(int i, int j){
        int rv = rooms[i][j];
        int [][] dirs = {{0,1},{-1,0},{0,-1},{1,0}};
        for(int k=0;k<4;k++) {
            int in = i + dirs[k][0];
            int jn = j + dirs[k][1];
            if(in<0 || in >=m || jn <0 || jn >= n) {
                continue;
            }
            int nv = rooms[in][jn];
            if(nv==-1 || nv<=(rv+1)) continue;
            rooms[in][jn] = rv + 1;
            wallsAndGates(in,jn);
        }
    }
    public void wallsAndGates(int[][] rooms) {
        this.rooms = rooms;
        this.m = rooms.length;
        this.n = rooms[0].length;
        for(int i=0;i<this.m;i++)
        {
            for(int j=0;j<this.n;j++){
                int rv = rooms[i][j];
                if(rv!=0) continue;
                wallsAndGates(i,j);
            }
             
        }
       
    }
}