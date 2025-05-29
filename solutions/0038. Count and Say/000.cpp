class Solution {
public:
    string countAndSay(int n) {
    string cns1="1", cns = "1" ;
    for(int i = 1; i < n; i++)
      {
        cns = cns1;
        cns1 = "";
        int c = 0;
        for(int j = 0;j < cns.length(); j++)
          {
            if(j-1<0 or cns[j] != cns[j-1])
              c = 1;
            else
              c++;
            if(j==cns.length()-1 or cns[j]!= cns[j+1])
              {
                string tmp1;
                tmp1 = std::to_string(c) + cns[j];
                cns1 += tmp1;
              }
          }
      }

    return cns1;
 
    }
};