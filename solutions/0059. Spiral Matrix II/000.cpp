class Solution {
public:
    vector<vector<int>> generateMatrix(int n) {        
        vector<vector<int>> matrix(n,vector<int>(n,0) );
        if(n==0) return matrix;
        
        int lb = 0,tb = 1,rb = n-1,bb = n -1;
        int k = 0;        
        int size = n*n;
        int i = 0, j = 0;
      
      
        int  cnt= 0;
        while(true)
        {
       //     cout<<"rb="<<rb<<endl;
            while(i<=rb)
            {
            //    cout<<"i="<<i<<" j="<<j<<endl;
                matrix[j][i]=k+1;
                i++;
                k++;
                if(k==size) return matrix;
                
            }
            i--;
            j++;
            rb--;
          //  cout<<"bb="<<bb<<endl;
            while(j<=bb)
            {
             //   cout<<"i="<<i<<" j="<<j<<endl;
                 matrix[j][i]=k+1;
                j++;
                k++;
                if(k==size) return matrix;
                
            }
            j--;
            i--;
            bb--;
          //  cout<<"lb="<<lb<<endl;
            while(i>=lb)
            {
             //   cout<<"i="<<i<<" j="<<j<<endl;
                 matrix[j][i]=k+1;
                i--;
                k++;
                if(k==size) return matrix;
            }
            i++;
            j--;
            lb++;
           // cout<<"tb="<<tb<<" j="<<j<<endl;
            while(j>=tb)
            {
               // cout<<"i="<<i<<" j="<<j<<endl;
                 matrix[j][i]=k+1;
                j--;
                k++;      
                if(k==size) return matrix;
            }
            j++;          
            tb++;
            i++;
         //  if (cnt>10)  return ve;
            cnt++;
         //   cout<<"cnt="<<cnt<<endl;
          
        }
        return matrix;
    }
};