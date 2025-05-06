class Solution {
    vector<vector<int>> vve;
    void permuteRecursive(vector<int>& nums,int k)
    {
        if(k == nums.size())
        {
            vve.push_back(nums);
            return;
        }
        std::unordered_set<int> myset = {};

        for(int i = k; i < nums.size(); i++)
        {
            /*
            cout<<"before find: k="<<k<<" i="<<i<<endl;
            cout<<"myset: ";
            for(auto it = myset.begin();it != myset.end();it++)
                cout<<*it<<" ";
            cout<<endl;
            cout<<"nums: ";
            for(auto it = nums.begin();it != nums.end();it++)
                cout<<*it<<" ";
            cout<<endl;
            */
            if(myset.find(nums[i]) != myset.end() )
                continue;
            //cout<<"after find:  k="<<k<<" i="<<i<<endl;
            myset.insert(nums[i]);
            int tmp = nums[k];
            nums[k] = nums[i];
            nums[i] = tmp;
            permuteRecursive(nums,k+1);
            nums[i] = nums[k];
            nums[k] = tmp;

        }
        return;
    }
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
         permuteRecursive(nums,0);
        return vve;
    }
};