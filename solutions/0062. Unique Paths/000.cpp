class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> path;
        for(int i = 0; i < m; i++)
        {
            vector<int> v(n,1);
            path.push_back(v);
        }
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(i==0 || j==0)
                    continue;
                path[i][j]=path[i-1][j] + path[i][j-1];
            }
        }
        return path[m-1][n-1];
    }
};