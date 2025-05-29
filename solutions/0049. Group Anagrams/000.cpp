class Solution {
    vector<int *> indexCharCnt;
    vector<int> indexSum;
    
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        for(int i =0; i < strs.size();i++)
        {
            int sum = 0;
            int * charCnt = new int[26];
            memset(charCnt,0,26*sizeof(int));
            for(int j = 0; j < strs[i].size();j++)
            {
                int charValue = strs[i][j]-'a';
                charCnt[charValue]++;
                sum +=charValue;
            }
            indexCharCnt.push_back(charCnt);
            indexSum.push_back(sum);
        }
        vector<vector<int>> veIndex;
        for(int i =0; i < strs.size(); i++ )
        {
            bool isNewGroup = true;
            for(int j = 0; j < veIndex.size(); j++)
            {
               int & index = veIndex[j][0];
               if(indexSum[i] != indexSum[index])
                   continue;
               if(strs[i].size() != strs[index].size())
                   continue;
               bool charMatched = true;
               for(int k =0;k < 26;k++)
               {
                   if(indexCharCnt[index][k] != indexCharCnt[i][k])
                   {
                       charMatched = false;
                       break;
                   }
               }
              if(charMatched)
              {
                  veIndex[j].push_back(i);
                  isNewGroup = false;
                  break;
              }
                   
            }
            if (isNewGroup)
            {
                vector<int> nv;
                nv.push_back(i);
                veIndex.push_back(nv);
            }                
        }
         vector<vector<string>> veStr;
        for(int i = 0; i < veIndex.size(); i++)
        {
            vector<string> v;
            for(int j =0; j < veIndex[i].size();j++)
            {
                v.push_back(strs[veIndex[i][j]]);
            }
            veStr.push_back(v);
        }
        return veStr;
    }
};