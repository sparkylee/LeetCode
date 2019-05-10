import org.junit.Test;

public  class lc289
{

    @Test
    public void test1()
    {

        int [][] board = {{0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}};
        tc(board);
    }
    private void tc(int [][] nums)
    {
        Solution s = new Solution();
        s.gameOfLife(nums);
        printBoard(nums);
    }
    private void printBoard(int[][] board)
    {
        if(board==null || board.length<1) return;
        int n = board.length;
        int m = board[0].length;
        for (int j = 0; j < n; j++)
         {
             for(int i=0;i< m;i++)
                System.out.print(board[j][i] + " ");
            System.out.println();
        }
    }

    class Solution {
        int [] deltas = {-1,0,1};
        int m,n;
        private int countLiveNeighbor(int[][] board,int i,int j)
        {
            int count = 0;
            for(int di:deltas)
                for(int dj:deltas)
                {
                    if(di==0 && dj==0) continue;
                    int ii = i + di, jj = j + dj;
                    if(ii>=0 && ii<this.m && jj>=0 && jj < this.n )
                        count +=((board[jj][ii] & 0x1));
                }
            return count;
        }


        public void gameOfLife(int[][] board) {
            if(board==null || board.length<1) return;
            this.n = board.length;
            this.m = board[0].length;
            for(int i=0;i<this.m;i++)
            {
                for(int j=0;j<this.n;j++)
                {
                    int count = countLiveNeighbor(board,i,j);
                    if(count==2)
                        board[j][i] |= (board[j][i] <<1) ;
                    if(count==3)
                        board[j][i] |= 0x2;
                }
            }
            for(int i=0;i<this.m;i++)
                for(int j=0;j<this.n;j++)
                    board[j][i] >>= 1;
        }
    }

}
