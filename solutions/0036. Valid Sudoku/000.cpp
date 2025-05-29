class Solution {
public:
    bool isDup(char c, vector<char> & block) {
        if(c!='.')
        {
            int num = c - 0x31;
            if(block[num] == '.') {block[num] = c;return false;}
            else {return true;}
        }
        return false;
        
    }
    bool isValidSudoku(vector<vector<char>>& board) {
        vector<char> block1(9,'.');
        vector<char> block(9,'.');
        for(int i=0; i < board.size(); i ++)
        {
            for(int k=0;k<9;k++){block[k]='.';};
            for(int j=0; j < board[i].size(); j++)
            {
                if(isDup(board[i][j],block)) {return false;};
            }
        }
        
        for(int i=0; i < board.size(); i ++)
        {
            vector<char> block(9,'.');
            for(int j=0; j < board[i].size(); j++)
            {
                if(isDup(board[j][i],block)) {return false;};
            }
        }

       for(int i = 0; i < 3; i ++)
            for(int j = 0; j < 3; j++)
            {   
                vector<char> block(9,'.');
                for(int m = 0; m < 3; m++)
                    for(int n = 0; n < 3; n++)
                    {
                        if(isDup(board[i*3+m][j*3+n],block)) {return false;};    
                    }
                
            }

        return true;
        
        
    }
};