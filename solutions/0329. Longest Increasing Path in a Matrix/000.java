class Solution {
 int m,n;
        private boolean isHigherDir(int[][] matrix,int i,int j, int i_next,int j_next)
        {
            if(i_next<0 || i_next>=n || j_next < 0 || j_next>=m) return false;
            return matrix[j_next][i_next]>matrix[j][i];
        }
        public int getlongestIncreasingPathFromCell(int[][] matrix,int[][] lens,int i,int j)
        {
            if(i<0 || i>=n || j < 0 || j>=m) return 0;
            int cv = lens[j][i];
            if(cv>0) return cv;
            cv = 1;
            int l=0,r=0,t=0,b=0;
            int nv_max = 0;
            int [][] nextDirs=new int[][]{{i-1,j},{i+1,j},{i,j-1},{i,j+1}};
            for(int [] nextIndex: nextDirs)
            {
                if(isHigherDir(matrix,i,j,nextIndex[0],nextIndex[1]))
                {
                    int nv=getlongestIncreasingPathFromCell(matrix,lens,nextIndex[0],nextIndex[1]);
                    nv_max = Math.max(nv_max,nv);
                }
            }
            cv +=nv_max;
            lens[j][i]=cv;
            return cv;
        }
        public int longestIncreasingPath(int[][] matrix) {
            this.m = matrix.length;
            if(this.m<1) return 0;
            this.n = matrix[0].length;
            int [][] lens = new int[m][n];
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                    lens[j][i]=0;
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                    getlongestIncreasingPathFromCell(matrix,lens,i,j);
            int cv = 0;
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                    cv = Math.max(cv,lens[j][i]);
            return cv;
        }
}