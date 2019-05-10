import org.junit.Test;

public  class lc130
{
    @Test
    public void test()
    {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        test(board);
    }
    @Test
    public void test1()
    {
        char[][] board = {{'O'},};
        test(board);
    }
    @Test
    public void test2()
    {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'}};
        test(board);
    }
    private void test(char[][] board)
    {
        Solution s = new Solution();
        s.solve(board);
        printBoard(board);
    }
    private void printBoard(char[][] board)
    {
        if(board==null || board.length<1) return;
        int height = board.length;
        int width = board[0].length;
        for (int j = 0; j < height; j++)
        {
            for(int i=0;i<width;i++)
            {
                System.out.print(board[j][i]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    class Solution {
        int width,height ;
        private void markDisclosedOAtPos(char[][] board,int i,int j)
        {
            if(getChar(board,i,j)!='O') return;
            if(getChar(board,i+1,j)==0 || getChar(board,i-1,j)==0
                    || getChar(board,i,j+1)==0 || getChar(board,i,j-1)==0) {
                board[j][i] = 0;
                markDisclosedOAtPos(board,i+1,j);
                markDisclosedOAtPos(board,i-1,j);
                markDisclosedOAtPos(board,i,j-1);
                markDisclosedOAtPos(board,i,j+1);
            }
        }
        private char getChar(char[][] board,int i,int j)
        {
            if(i<0 || i>= this.width || j<0 || j>=this.height) return 0;
            return board[j][i];
        }
        public void solve(char[][] board) {
            if(board==null || board.length<1) return;
            this.height = board.length;
            this.width = board[0].length;
            for(int i=0;i<this.width;i++)
                for(int j=0;j<this.height;j++)
                    markDisclosedOAtPos(board,i,j);
            for(int i=0;i<this.width;i++)
                for(int j=0;j<this.height;j++)
                {
                    if(board[j][i]=='O')
                    {
                        board[j][i] = 'X';
                        continue;
                    }
                    if(board[j][i]==0)
                    {
                        board[j][i] = 'O';
                        continue;
                    }
                }
        }
    }

}
