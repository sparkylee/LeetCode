class Solution {
    vector<vector<int>> vve;
    void combinationSumRecursive(vector<int>& candidates, vector<int> v, int i , int target) {
        if( i >= candidates.size())
            return;
        if(target <= 0)
            return;
        int residue = target;
        int k = 0;
        while(residue > 0)
        {
            residue = target - candidates[i] * k;
            if(residue == 0)
            {

                v.push_back(candidates[i]);
                vve.push_back(v);
                return;
            }
            if(residue < 0)
            {
                return;
            }
            if(k > 0)
                v.push_back(candidates[i]);
            combinationSumRecursive(candidates,v,i+1,residue);
            k ++;
        }
        return;
    }
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vve.clear();
        vector<int> v;
        combinationSumRecursive(candidates, v, 0, target);
        return vve;
    }
};