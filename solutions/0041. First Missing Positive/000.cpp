class Solution {
    const bool dout0 = false;
    int shapeNums(vector<int>& nums)
    {
        int limit = 0; 
        limit = nums.size();
        for(int i = 0; i < nums.size(); i ++)
        {
            if(nums[i] > limit || nums[i] < 0)
                nums[i] = 0;
        }
        return limit;
    }
    
public:
    int firstMissingPositive(vector<int>& nums) {
        if(nums.empty())
            return 1;
        vector<int> s;
        int limit = shapeNums(nums);
        for(int i = 0; i < limit/32 + 1; i++)
        {
            s.push_back(0);
        }
        for(int i = 0; i < nums.size(); i++)
        {
            int mask  = 1 << (nums[i] % 32);
            int index = nums[i] / 32;
            s[index] = s[index] | mask;
        }
        if(dout0)
        {
            for(int i = 0; i < s.size(); i++)
            {
                std::bitset<32> x(s[i]);
                cout<<x<<endl;
            }
        }
        for(int i = 1; i < nums.size() + 1; i++)
        {
            int mask = 1 << (i % 32);
            int index = i / 32;
            if(dout0)
            {
                 std::bitset<32> x(mask);
                 cout<<"mask:"<<x<<endl;
                 std::bitset<32> y(s[i]);
                cout<<"index:"<<y<<endl;
            }
            if( (s[index] & mask) == 0)
                return i;
        }
        return limit+1;
        
        
        
    }
};