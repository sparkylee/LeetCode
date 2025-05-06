class Solution {
    const bool dout0 = false;
    const bool dout1 = false;
    const bool dout2 = false;
    const bool dout3 = false;
    const bool dex0 = false;
    vector<vector<set<char>>> fillset;
    vector<vector<int>> hset;
    vector<vector<int>> vset;
    vector<vector<int>> gset;
    ///////////////////////////////

     bool validateChar(vector<vector<char>>& board,int i, int j, char c)
    {
        if(!validateHorizontal(board,i,j,c))
        {
            if(dout2)
                cout<<"failed to validateHorizontal"<<endl;
            return false;
        }
        if(!validateVertical(board,i,j,c))
        {
            if(dout2)
                cout<<"failed to validateVertical"<<endl;
            return false;
        }
        if(!validateGrid(board,i,j,c))
        {
            if(dout2)
                cout<<"failed to validateGrid"<<endl;
            return false;
        }

        return true;
    }
    /*
    void insertChar(vector<vector<char>> & board,int i, int j, char c)
    {
        board[i][j] = c;
    }
    void eraseChar(vector<vector<char>> & board,int i, int j, char c)
    {
        board[i][j] = '.';
    }
    */


    bool validateHorizontal(vector<vector<char>>& board,int i, int j, char c)
    {

        for(int k = 0; k < 9; k ++)
        {
            if( board[i][k]== c)
                return false;
        }
         if(dout0)
            cout<<"validateHorizontal pass:"<<i<<" "<<j<<endl;
        return true;
    }
    bool validateVertical(vector<vector<char>>& board,int i, int j, char c)
    {

        for(int k = 0; k < 9; k ++)
        {
            if( board[k][j]==c)
                return false;
        }
         if(dout0)
         cout<<"validateVertical pass:"<<i<<" "<<j<<endl;
        return true;
    }
    bool validateGrid(vector<vector<char>>& board,int i, int j,char c)
    {
        int start_i = 0;
        int start_j = 0;
        start_i = (i / 3)*3;
        start_j = (j / 3)*3;


        for(int ii = start_i; ii < start_i + 3; ii++)
        {
            for(int jj = start_j; jj < start_j + 3; jj++)
            {
                if(  board[ii][jj]== c)
                    return false;
            }
        }
        if(dout0)
        {
            cout<<"validateGrid pass:"<<i<<" "<<j<<" ingrid "<<start_i<<" "<<start_j<<endl;
        }
        return true;
    }

    ////////////////////////////

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
    ////////////////////////////
    void printSingleVectorSet(vector<vector<int>> sv)
    {
        std::vector<int>::iterator it;
        for(int i = 0; i < sv.size(); i++)
        {
            for (it = sv[i].begin(); it != sv[i].end(); ++it)
            {
                cout<< *it<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
    }
    void initSet(vector<vector<char>> & board)
    {
        vector<int> s;
        for(int m=0; m < 9; m++)
        {
            s.push_back(0);
        }
        for(int k = 0; k < 9; k ++)
        {
            gset.push_back(s);
            hset.push_back(s);
            vset.push_back(s);
        }
        for(int i = 0; i < 9; i ++)
        {
            for(int j = 0; j < 9; j ++)
            {
                if(board[i][j] != '.')
                {
                    int c = board[i][j] - '1';
                    hset[i][c] = 1;
                    vset[j][c] = 1;
                    gset[gIndex(i,j)][c] = 1;
                }
            }
        }

    }

    int gIndex(int i, int j)
    {
        return (i / 3)* 3 + j / 3;
    }

    bool validateChar1(vector<vector<char>> & board, int i, int j, char c)
    {
        return (gset[gIndex(i,j)][c-'1'] == 0) && (hset[i][c-'1'] == 0) && (vset[j][c-'1']==0);
    }

    void insertChar(vector<vector<char>> & board,int i, int j, char c)
    {
        board[i][j] = c;
        gset[gIndex(i,j)][c-'1'] = 1;
        hset[i][c-'1'] = 1;
        vset[j][c-'1'] = 1;
    }
    void eraseChar(vector<vector<char>> & board,int i, int j, char c)
    {
        board[i][j] = '.';
        gset[gIndex(i,j)][c-'1'] = 0;
        hset[i][c-'1'] = 0;
        vset[j][c-'1'] = 0;
    }

   ////////////////////////////
    bool solveSudokuRecursive(vector<vector<char>> & board, int count) {
        if(count==81)
            return true;
        int i = count / 9;
        int j = count % 9;

        if(board[i][j] == '.')
        {
            for(int k=1; k <=9;k++ )
            {

                char c = (char)('0' + k);
                //bool validPoint = validateChar(i,j,c);
          //      bool validPoint  = validateChar(board,i,j,c);
                bool validPoint = validateChar1(board,i,j,c);
                if(dout1)
                {
                    cout<<"validating: "<< c << " at "<<i << " "<<j<<"  "<<validPoint<<endl;
                }
              //  if(validPoint != validPoint1)
                if(false)
                {
              //      cout<<"wrong: "<< c << " at "<<i << " "<<j<<"   "<<validPoint<<" "<<validPoint1<<endl;
                    printboard(board);
                    printSingleVectorSet(hset);
                    printSingleVectorSet(vset);
                    printSingleVectorSet(gset);
                    return true;
                }

                if(validPoint)
                {
                    //insertChar(board,i,j,c);
                    insertChar(board,i,j,c);
                    bool validSudoku = solveSudokuRecursive(board,count+1);
                    if(validSudoku)
                        return validSudoku;

                    //board[i][j] = '.';
                    //eraseChar(board,i,j,c);
                    eraseChar(board,i,j,c);
                }
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
        initSet(board);
      //  printboard(board);
    //    printSingleVectorSet(hset);
    //    printSingleVectorSet(vset);
    //    printSingleVectorSet(gset);
    //    return;
        solveSudokuRecursive(board,0);
  //      printboard(board);
        return;
    }
};
