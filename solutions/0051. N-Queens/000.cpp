class Solution {
    vector<vector<string>>  vv;
    
    bool isSafeGrid(vector<string> & ve,int k, int i)
    {        
        for(int y = k-1;y>=0;y--)
        {
            if(ve[y][i]=='Q') return false;
        }
        int d = 1;
        while(k-d>=0 && i-d>=0)
        {
            if(ve[k-d][i-d]=='Q') return false;
            d++;
        }
        d = 1;
        while(k-d>=0 && i+d < ve.size())
        {
            if(ve[k-d][i+d]=='Q') return false;
            d++;
        }
        
        return true;
    }
    void searchNQueens(vector<string> & ve,int k)
    {
        if(k==ve.size())
        {
            vv.push_back(ve);
            return;
        }
         
        for(int i=0;i<ve.size();i++)
        {
            if(isSafeGrid(ve,k,i))
            {
                ve[k][i]='Q';
                searchNQueens(ve,k+1);
                ve[k][i]='.';
            }
        }
        return;
    }
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<string> ve;
        for(int i=0; i < n; i++)
        {
            string s(n,'.');
            ve.push_back(s);
        }
        searchNQueens(ve,0);
        return vv;
    }
};