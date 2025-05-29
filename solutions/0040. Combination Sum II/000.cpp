class Solution {
    const bool dout0 = false;
    /////////////
    vector<vector<int>> ve;
    void printvector(vector<int>& candidates)
    {
        for(int i = 0; i < candidates.size(); i++)
        {
            cout<<candidates[i]<<" ";
        }
        cout<<endl;
    }
    void printVe()
    {
        for(int i = 0; i < ve.size(); i ++)
        {
            for(int j = 0; j < ve[i].size(); j ++)
            {
                cout<<ve[i][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
    }
    void printSelected(vector<int> & sel)
    {
          for(int i = 0; i < sel.size(); i ++)
          {
              cout<<sel[i]<<" ";
          }
    }
    int getNextIndex(vector<int>& candidates,int i)
    {
        int k = i; 
        while(k < candidates.size())
        {
            if(candidates[k] != candidates[i])
                return k;
            k ++;
        }
        return k;
    }
    void combinationSum2Recursive(vector<int>& candidates,int i, vector<int> & selected,  int target)
    {
        if(target == 0)
        {
            ve.push_back(selected);
            return;
        }
        if(i >= candidates.size())
            return;
        if(target < candidates[i])
            return;
        
        int N = getNextIndex(candidates, i);
        for(int j = i; j <= N; j ++)
        {
            if(j-i>0)
            {
               selected.push_back(candidates[i]);
            }
            if(dout0)
            {
                cout<< " i="<<i<<" ";
                printSelected(selected);
                cout<<"target="<<target - candidates[i] * (j-i)<<" ";
                cout<<"N="<<N<<" j="<<j<<endl;
            }
            combinationSum2Recursive(candidates,N,selected,target - candidates[i] * (j-i));
        }
        for(int j = i ; j < N; j ++)
        {
            selected.pop_back();
        }
        return ;
    }
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        std::sort(candidates.begin(),candidates.end());
        if(dout0)
            printvector(candidates);
        vector<int> selected;
        selected.reserve(candidates.size());
        combinationSum2Recursive(candidates,0,selected,target);
       // printVe();
        return ve;
    }
};


