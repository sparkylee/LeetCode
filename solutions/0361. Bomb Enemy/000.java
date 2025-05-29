class Solution {
    public void checkRows(char[][] grid, int [][] igrid) {
        int m = grid.length;
        int n = grid[0].length;
        int j = 0;
        int enemies = 0;
        for(int i=0;i<m;i++) {
            while(j<=n) {
                if(j>=n || grid[i][j]=='W') {
                    if(enemies==0)
                    {
                        j++;
                        continue;
                    }
                    int k = j - 1;
                    while(k>=0 && grid[i][k]!='W') {
                        if(grid[i][k]=='0') {
                            igrid[i][k] = enemies;
                        }
                        k--;
                    }
                    enemies = 0;
                    j++;
                    continue;
               }               
               if(grid[i][j]=='E') {
                     enemies ++;
                     j++;
                     continue;
               }              
               // grid[i][j]=='0'
               j++;                   
            }
            j = 0;      
            enemies = 0;
        }
    }
     public void checkColumns(char[][] grid, int [][] igrid) {
        int m = grid.length;
        int n = grid[0].length;      
        int i = 0;
        int enemies = 0;
        for(int j=0;j<n;j++) {
            while(i<=m) {
                if(i>=m || grid[i][j]=='W') {
                    if(enemies==0)
                    {
                        i++;
                        continue;
                    }
                    int k = i - 1;
                    while(k>=0 && grid[k][j]!='W') {
                        if(grid[k][j]=='0') {
                            igrid[k][j] = igrid[k][j] | enemies << 16;
                        }
                        k--;
                    }
                    enemies = 0;
                    i++;
                    continue;
               }               
               if(grid[i][j]=='E') {
                     enemies ++;
                     i++;
                     continue;
               }              
               // grid[i][j]=='0'
               i++;                   
            }
            i = 0;      
            enemies = 0;
        }
    }
     public int sumMax(int [][] igrid) {
        int m = igrid.length;
        int n = igrid[0].length; 
        int mv = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                 int value = igrid[i][j];
                 int rv = value >> 16;
                 int cv = value & 0xffff;
                 int sum = rv + cv;
                 mv = sum > mv ? sum: mv;
            }           
        }
        return mv;
    }
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int [][] igrid =  new int[m][n];;
        checkRows(grid, igrid);
        checkColumns(grid, igrid);
        int mv = sumMax(igrid);
        return mv;
    }
}