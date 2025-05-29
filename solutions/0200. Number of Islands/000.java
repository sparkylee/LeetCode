class Solution {
     int width,height ;
        int count = 0;
        private void markCheckedLand(char[][] board,int i,int j)
        {
            if(getChar(board,i,j)!='1') return;
            if(getChar(board,i+1,j)!='2' && getChar(board,i-1,j)!='2'
                    && getChar(board,i,j+1)!='2' && getChar(board,i,j-1)!='2') {
                        this.count ++;
                            }
            board[j][i] = '2';
            markCheckedLand(board,i+1,j);
            markCheckedLand(board,i-1,j);
            markCheckedLand(board,i,j-1);
            markCheckedLand(board,i,j+1);
        }
        private char getChar(char[][] board,int i,int j)
        {
            if(i<0 || i>= this.width || j<0 || j>=this.height) return '0';
            return board[j][i];
        }
        public int numIslands(char[][] grid) {
            if(grid==null || grid.length<1) return 0;
            this.height = grid.length;
            this.width = grid[0].length;
            for(int i=0;i<this.width;i++)
                for(int j=0;j<this.height;j++)
                    markCheckedLand(grid,i,j);

            return this.count;
        }
}