const bool dout0 = false;
int checkLeftMost( vector<int>& nums, int lIndex, int rIndex, int i)
{
    if(dout0)
        cout<<"In checking checkLeftMost: lIndex="<<lIndex<<" rIndex="<<rIndex<<" i="<<i<<endl;
    if ((i==lIndex) ||( nums[i] != nums[i-1]))
        return 0;
    else
        return -1;
}
int checkRightMost(vector<int>& nums,int lIndex, int rIndex,int i)
{
    if ((i==rIndex) || (nums[i] != nums[i+1]))
        return 0;
    else
        return 1;
}
class Solution {
    int searchRangeDir(vector<int>& nums, int lIndex, int rIndex,int target,  int (*checkDir)(vector<int>& , int , int , int))
    {
        if(lIndex > rIndex)
            return -1;
        int i = (lIndex + rIndex) / 2;
        int dir = checkDir(nums,lIndex,rIndex,i);
        if(nums[i] == target && dir == 0)
        {
            return i;
        }
        bool lShift = (nums[i] == target && (dir == -1) );
        bool rShift = (nums[i] == target && (dir ==  1) );
        if(nums[i] > target || lShift)
        {
            rIndex = i - 1;
        }
        if(nums[i] < target || rShift)
        {
            lIndex = i + 1;
        }
        if(dout0)
            cout<<"In searchRangeDir1: lIndex="<<lIndex<<" rIndex="<<rIndex<<" i="<<i<<endl;
        return searchRangeDir(nums, lIndex, rIndex, target, checkDir);

    }
public:
    vector<int> searchRange(vector<int>& nums, int target) {
           vector<int> ve;
           int lIndex = searchRangeDir(nums,0,nums.size()-1, target,checkLeftMost);
           int nL = 0;
           if(lIndex != -1)
             nL = lIndex;
           int rIndex = searchRangeDir(nums,nL,nums.size()-1, target,checkRightMost);
           ve.push_back(lIndex);
           ve.push_back(rIndex);
           return ve;
    }
};