class Solution {
    vector<int> fea;
    void printFea()
    {
        for(int i =0; i < this->fea.size(); i++)
        {
            cout<<this->fea[i]<<" ";
        }
        cout<<endl;
        cout<<endl;
    }
public:
    bool canJump(vector<int>& nums) {
        for(int i=0; i < nums.size(); i++) fea.push_back(0);
        //cout<<"fea_size="<<fea.size()<<endl;
         
        fea.back() = 1;
        for(int i = nums.size() - 2; i >= 0; i --)
        {
            bool reachable = false;
            
            for(int j = nums[i]; j >= 1;j--)
            {
               // cout<<"i="<<i<<" j="<<j<<endl;
           //     printFea();
                int k = i + j;                
                if(k>=nums.size() || fea[k] == 1)
                {
                    //cout<<"k="<<k<<" fea[k]="<<fea[k]<<endl;
                    reachable = true;
                    break;
                }
            }
            if(reachable)
                fea[i] = 1;
        }
         //printFea();
        return fea[0]==1;
    }
};