class Solution {
    const bool dout0 = false;
    const bool dout1 = false;
    const bool dout2 = false;
    const bool dout3 = false;
    const bool dex0 = false;
    void printboard(vector<vector<char>> & ve)
    {
        for(int i=0; i < ve.size(); i ++)
        {
            for(int j=0; j < ve[i].size(); j ++)
            {
                cout<<ve[i][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
        
    }
    bool validateFill(vector<vector<char>>& board,int i, int j)
    {
        if(!validateHorizontal(board,i,j))
        {
            if(dout2)
                cout<<"failed to validateHorizontal"<<endl;
            return false;
        }
        if(!validateVertical(board,i,j))
        {
            if(dout2)
                cout<<"failed to validateVertical"<<endl;
            return false;
        }
        if(!validateGrid(board,i,j))
        {
            if(dout2)
                cout<<"failed to validateGrid"<<endl;
            return false;
        }
            
        return true;
    }
    bool validateHorizontal(vector<vector<char>>& board,int i, int j)
    {
       
        for(int k = 0; k < 9; k ++)
        {
            if(k != j && board[i][k]==board[i][j])
                return false;
        }
         if(dout0)
            cout<<"validateHorizontal pass:"<<i<<" "<<j<<endl;
        return true;
    }
    bool validateVertical(vector<vector<char>>& board,int i, int j)
    {
       
        for(int k = 0; k < 9; k ++)
        {
            if(k != i && board[k][j]==board[i][j])
                return false;
        }
         if(dout0)
         cout<<"validateVertical pass:"<<i<<" "<<j<<endl;
        return true;
    }
    bool validateGrid(vector<vector<char>>& board,int i, int j)
    {
        int start_i = 0;
        int start_j = 0;
        start_i = (i / 3)*3;
        start_j = (j / 3)*3;
        
       
        for(int ii = start_i; ii < start_i + 3; ii++)
        {
            for(int jj = start_j; jj < start_j + 3; jj++)
            {
                if( (ii!=i || jj!=j) && board[ii][jj]==board[i][j])
                    return false;
            }
        }
        if(dout0)
        {
            cout<<"validateGrid pass:"<<i<<" "<<j<<" ingrid "<<start_i<<" "<<start_j<<endl;
        }
        return true;
    }
    bool solveSudokuRecursive(vector<vector<char>> & board, int count) {
      //  cout<<"count="<<count<<endl;
        if(dex0)
        {
            if(count==1)
            {
              //  printboard(board);
                return true;
            }
            
        }
        if(count==81)
            return true;
        int i = count / 9;
        int j = count % 9;

        if(board[i][j] == '.')
        {
            for(int k=1; k <=9;k++ )
            {
                board[i][j] = (char)('0' + k);
                
                if(dout1)
                {
                    cout<<"validating: "<<(char)(board[i][j]) << " at "<<i << " "<<j<<endl;
                }
                bool validFill = validateFill(board,i,j);
                if(validFill)
                {
                    bool validSudoku = solveSudokuRecursive(board,count+1);
                    if(validSudoku)
                        return validSudoku;
                }
                board[i][j] = '.';
            }
            return false;
        }
        else
        {
             return solveSudokuRecursive(board,count+1);
        }   
        return false;
    }
public:
    void solveSudoku(vector<vector<char>>& board) {
        solveSudokuRecursive(board,0);
        //printboard(board);
        return;
    }
};



