class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = m==0?0 :matrix[0].size();
        int lb = 0,tb = 1,rb = n-1,bb = m -1;
        int k = 0;
        int size = m*n;
        int i = 0, j = 0;
        vector<int> ve;
        if(n==0) return ve;
        int  cnt= 0;
        while(true)
        {
       //     cout<<"rb="<<rb<<endl;
            while(i<=rb)
            {
            //    cout<<"i="<<i<<" j="<<j<<endl;
                ve.push_back(matrix[j][i]);
                i++;
                k++;
                if(k==size) return ve;

            }
            i--;
            j++;
            rb--;
          //  cout<<"bb="<<bb<<endl;
            while(j<=bb)
            {
             //   cout<<"i="<<i<<" j="<<j<<endl;
                ve.push_back(matrix[j][i]);
                j++;
                k++;
                if(k==size) return ve;

            }
            j--;
            i--;
            bb--;
          //  cout<<"lb="<<lb<<endl;
            while(i>=lb)
            {
             //   cout<<"i="<<i<<" j="<<j<<endl;
                ve.push_back(matrix[j][i]);
                i--;
                k++;
                if(k==size) return ve;
            }
            i++;
            j--;
            lb++;
           // cout<<"tb="<<tb<<" j="<<j<<endl;
            while(j>=tb)
            {
               // cout<<"i="<<i<<" j="<<j<<endl;
                ve.push_back(matrix[j][i]);
                j--;
                k++;
                if(k==size) return ve;
            }
            j++;
            tb++;
            i++;
         //  if (cnt>10)  return ve;
            cnt++;
         //   cout<<"cnt="<<cnt<<endl;

        }
        return ve;
    }
};