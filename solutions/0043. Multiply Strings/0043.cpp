class Solution {
    const bool dout0 = false;
    void str2ve(string num, vector<int> & x)
    {
        int k = 0;
        for(int i = num.size() - 1; i >= 0; i --)
        {
            x[k] = num[i] - '0';
            k++;
        }
        return;
    }

    void accumulate(vector<int> & a, vector<int> & b, int M, int N)
    {
        int cin = 0;
        int sum = 0;
        int i = M;
        for( ; i <= N; i ++)
        {
            int tmp = a[i] + b[i] + cin;
            if(tmp >= 10)
            {
                 sum = tmp-10;
                 cin = 1;
            }
            else
            {
                 sum = tmp;
                 cin = 0;
            }
            b[i] = sum;
            a[i] = 0;//reset a
        }
        while(cin > 0)//deal with the cin
        {
            int tmp = b[i] + cin;
             if(tmp >= 10)
            {
                 sum = tmp-10;
                 cin = 1;
            }
            else
            {
                 sum = tmp;
                 cin = 0;
            }
            b[i] = sum;
            i++;
        }
        return;
    }
    void printvector(vector<int> & x)
    {

        int N = 0;
        for(int j = x.size() - 1; j >= 0; j--)
        {
            if(x[j]==0)
                continue;
            else
            {
                N=j;
                break;
            }
        }
        for(int i = N; i >= 0; i --)
        {
            cout<<x[i];
        }
        cout<<endl;
    }
    string int2str(vector<int> & x)
    {
        int N = 0;
        for(int j = x.size() - 1; j >= 0; j--)
        {
            if(x[j]==0)
                continue;
            else
            {
                N=j;
                break;
            }
        }
        string s;
        for(int i = N; i >= 0; i --)
        {
            s += x[i]+'0';
        }
        return s;
    }

public:
    string multiply(string num1, string num2) {
        vector<int> a(220,0),b(220,0),c(220,0),d(220,0);
        int N1 = num1.size();
        int N2 = num2.size();
        str2ve(num1,a);
        str2ve(num2,b);
        int k = 0;
        for(int i = 0; i < N1; i++)
        {
            for(int j = 0; j < N2; j++)
            {
                k = i + j;
                int tmp = a[i]*b[j];
                c[k]   = tmp % 10;
                c[k+1] = tmp / 10;
                if(dout0)
                {
                    cout<<"i="<<i<<" j="<<j<<endl;
                    printvector(c);
                    printvector(d);
                    cout<<endl;
                }
                accumulate(c,d,k,k+1);
            }
        }
        if(dout0)
        {
            printvector(d);
            cout<<endl;
        }
        return int2str(d);
    }

};
/*
"138448"
"155234523"
"33"
"109"
"8345245979045284"
"239947653648586"
"0"
"0"
*/
