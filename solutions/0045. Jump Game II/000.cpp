
class Solution {
public:
    int jumpRe(vector<int>& nums,
               vector<int>& ns, vector<int>& ps,
               int i,int k)
    {
        if ( i >= nums.size() ) return 0;
        if (k < ps[i]) ps[i] = k;
        if (ns[i] == -1)
        {
            ns[i]  = (int)nums.size();
            for(int x = nums[i];x>=1;x--)
            {
                int s = jumpRe(nums,ns,ps,i+x,k+1);
                ns[i] = min(ns[i],s+1);
                if (ns[i] <= 2)
                    break;
            }
        }
        return ns[i];
    }

    int jump(vector<int>& nums) {
        int n = (int)(nums.size());
        if (n <= 1) return 0;
        vector<int> ns(n,-1),ps(n,n);
        ps[0] = 0;
        ns.back() = 0;
        int r = this->jumpRe(nums,ns,ps,0,0);
        return r;
    }
};