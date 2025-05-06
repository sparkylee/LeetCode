class Solution {
    const bool dout0 = false;
    const bool dout1 = false;
    const bool dout2 = false;
    void g2l(vector<int>& nums, int loc)
    {
        int endLoc =  (nums.size() + loc - 1 ) / 2 ;
        for(int i = loc; i <= endLoc; i ++)
        {
            int tmp = 0;
            tmp = nums[i] ;
            int index = nums.size() - 1 - (i - loc);
            nums[i] = nums[index];
            nums[index] = tmp;
            if(dout0)
            {
                cout<<"Switching indice are:  "<< i << "  " <<index<<endl;
            }
        }
        return;
    }
    void searchReplace(vector<int>& nums, int minIndex)
    {
        int tmp = 0;
        tmp = nums[minIndex - 1 ];
        for(int i = minIndex; i < nums.size(); i ++)
        {
            if(nums[i] <= tmp)
                {
                    nums[minIndex - 1 ] = nums[i-1];
                    nums[i-1] = tmp;
                    return;
                }
        }

        nums[minIndex - 1] = nums.back();
        nums.back() = tmp;
        return ;
    }
public:
    void nextPermutation(vector<int>& nums) {
         int loc = -1;
         if(dout0)
         {
              cout<<"The initial nums vector : ";
              for(int i = 0; i < nums.size(); i ++)
                    cout<<nums[i] << "  ";
              cout<<endl;

         }
         for(int i = nums.size() - 1; i > 0; i --)
         {
             if(i-1 >= 0 && nums[i - 1] < nums[i])
             {
                 if(dout1)
                 {
                     cout<<"searching for the going-down location: " << i ;
                 }
                 loc = i-1;
                 break;
             }
         }
         if(dout0)
            cout<<"The location to find next permutation: " <<loc<<endl;
         if(loc != -1)
            searchReplace(nums,loc+1);
         if(dout0)
            {   cout<<"nums after searchPlace: ";
                for(int i = 0; i < nums.size(); i ++)
                    cout<<nums[i] << "  ";
                cout<<endl;
            }
         g2l(nums, loc+1);
         return ;

    }
};