class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int n = matrix.size();
        int layer = n%2==0?n/2:(n/2)+1;
        for(int i =0;i <layer; i++)
        {
            int len = n - 1 - 2*i;
           // cout<<"len="<<len<<endl;
            for(int j = 0; j < len; j++)
            {
            //    cout<<"i="<<i<<" j="<<j<<endl;
                int x0,y0,x1,y1,x2,y2,x3,y3;
                x0 = i;
                y0 = j+i;
                x1 = i+len-j;
                y1 = i;
                x2 = i+len;
                y2 = i+len-j;
                x3 = i+j;
                y3 = i+len;
                /*

                cout<<"x0="<<x0<<" y0="<<y0<<endl;
                cout<<"x1="<<x1<<" y1="<<y1<<endl;
                cout<<"x2="<<x2<<" y2="<<y2<<endl;
                cout<<"x3="<<x3<<" y3="<<y3<<endl;

                */
                int tmp = matrix[y0][x0];
                matrix[y0][x0] = matrix[y3][x3];
                matrix[y3][x3] = matrix[y2][x2];
                matrix[y2][x2] = matrix[y1][x1];
                matrix[y1][x1] = tmp;
                /*
                for(int a = 0; a<matrix.size();a++)
                {
                    for(int b =0; b< matrix[a].size();b++)
                    {
                        cout<<matrix[a][b]<<" ";
                    }
                    cout<<endl;
                }
                cout<<endl;
                */
            }
        }
    }
};