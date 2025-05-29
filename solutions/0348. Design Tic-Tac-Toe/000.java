class TicTacToe {
    int [] [] board;
    int n;
    public TicTacToe(int n) {
        this.board = new int[n][n];
        this.n=n;
    }
    
    public int move(int row, int col, int player) {
        this.board[row][col] = player;
        boolean isInRow = true;
        for(int i=row+1;i<this.n;i++)
        {
            if(this.board[i][col]!=this.board[i-1][col]) {
                isInRow = false;
                break;    
            }
                
        }
        if(isInRow) {
            for(int i=row-1;i>=0;i--)
            {
                if(this.board[i][col]!=this.board[i+1][col]) {
                    isInRow = false;
                    break;
                }                    
            }
        }
        if(isInRow){
            // System.out.println("isInRow");
            return player;
        }
            
        boolean isInCol= true;
        for(int i=col+1;i<this.n;i++)
        {
            if(this.board[row][i]!=this.board[row][i-1]) {
                isInCol = false;
                break;
            }
                
        }
        if(isInCol) {
            for(int i=col-1;i>=0;i--)
            {
                if(this.board[row][i]!=this.board[row][i+1]) {
                    isInCol = false;
                    break;
                }
                    
            }
        }
        if(isInCol){
            // System.out.println("isInCol");
            return player;
        }
        if(row==col) {
            boolean isInDia0 = true;
            for(int i=row+1;i<this.n;i++)
            {
                if(this.board[i][i]!=this.board[i-1][i-1]) {
                    isInDia0 = false;
                    break;
                }                    
            }
            if(isInDia0) {
                for(int i=row-1;i>=0;i--)
                {
                    if(this.board[i][i]!=this.board[i+1][i+1]) {
                        isInDia0 = false;
                        break;
                    }
                        
                }   
            }
            if(isInDia0) {
                // System.out.println("isInDia0");
                return player;      
            }
                            
        }
        if(row+col==(n-1)) {
            boolean isInDia1 = true;
            for(int i=row+1;i<this.n;i++)
            {
                if(this.board[i][n-1-i]!=this.board[i-1][n-1-(i-1)]) {
                    isInDia1 = false;
                    break;
                }                    
            }
            if(isInDia1) {
                for(int i=row-1;i>=0;i--)
                {
                    if(this.board[i][n-1-i]!=this.board[i+1][n-1-(i+1)]) {
                        isInDia1 = false;
                        break;
                    }                        
                }   
            }
            if(isInDia1) {
                // System.out.println("isInDia1");
                return player;     
            }
                
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */