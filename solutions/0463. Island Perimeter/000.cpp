class Solution {
public:
    int islandPerimeter(vector<vector<int>>& grid) {
        int count  = 0;
        for(int i = 0; i < grid.size();i++)
        {            
            for(int j = 0; j < grid[i].size(); j++)
            {
                if(grid[i][j]==1)
                {
                    if(i-1<0 ) count++;
                    if(j-1<0) count++;
                    if(i+1==grid.size()) count++;
                    if(j+1==grid[i].size()) count++;
                    
                }
              
                if(j+1<grid[i].size()) 
                {
                    if(grid[i][j] != grid[i][j+1])
                        count++;
                }
                if(i+1<grid.size())
                {
                    if(grid[i][j] != grid[i+1][j])
                         count++;
                }
                        
            }
       //      cout<<"i="<<i<<" count="<<count<<endl;       
        }
        return count;
    }
};