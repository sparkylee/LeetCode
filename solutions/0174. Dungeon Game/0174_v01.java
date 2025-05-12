class Solution {
    int m,n;

    private void calcHealth(int[][] dungeon,int[][] health,int i, int j)
    {
        int mv = 0;
        if(i+1>=n && j+1<m)
            mv = health[j+1][i];
        if(i+1<n && j+1>=m)
            mv = health[j][i+1];
        if(i+1<n && j+1<m )
            mv = Math.min(health[j+1][i],health[j][i+1]);

        int d = dungeon[j][i];
        int healthReqWithoutDungeon = Math.max(1,mv);
        int healthReqWithDungeon = healthReqWithoutDungeon - d;
        health[j][i] = healthReqWithDungeon;
    }
    public int calculateMinimumHP(int[][] dungeon) {
        m = dungeon.length;
        if(m<1) return 0;
        n = dungeon[0].length;
        int[][] health = new int[m][n];
        int ii = n-1,jj=m-1;
        while(ii>=0 && jj>=0)
        {
            for(int i=ii;i>=0;i--) calcHealth(dungeon,health,i,jj);
            for(int j=jj-1;j>=0;j--) calcHealth(dungeon,health,ii,j);
            ii--;
            jj--;
        }
        return health[0][0]>0?health[0][0]:1;
    }
}