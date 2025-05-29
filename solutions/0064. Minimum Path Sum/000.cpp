class Solution {
    vector<vector<int>> vv;
    int m,n;
    int searchMinPathSum(vector<vector<int>>& grid,int i, int j)
    {
       
       if(i>=m || j>= n ) 
       {
           //cout<<"i="<<i<<" j="<<j<<endl;
           return -1;
       }
       if(vv[i][j]>=0) 
       {
          // cout<<"i="<<i<<" j="<<j<<" vv="<<vv[i][j]<<endl;
           return vv[i][j];
       }
       int mv = -1;
       int bv =searchMinPathSum(grid,i+1,j) ;       
       int lv = searchMinPathSum(grid,i,j+1);
       if(bv<0) mv = lv;
       else 
       {
           if(lv<0) mv = bv;
           else
           {
               mv = min(bv,lv);
           }
       }
       
      
       vv[i][j]= mv + grid[i][j];
       // printMatrix(vv);
       return vv[i][j];
    }
    void printMatrix(vector<vector<int>>& grid)
    {
        for(int i=0;i<grid.size();i++)
        {
            for(int j =0;j < grid[i].size();j++)
            {
                cout<<grid[i][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
    }
public:
    int minPathSum(vector<vector<int>>& grid) {        
        m = grid.size();
        n = grid[0].size();
        if(n==0) return 0;
        for(int i=0;i < m ; i++)
        {
            vv.push_back(vector<int>(n,-1));
        }
        vv.back().back()=grid.back().back();
       // printMatrix(vv);
        return searchMinPathSum(grid,0,0);
    }
};