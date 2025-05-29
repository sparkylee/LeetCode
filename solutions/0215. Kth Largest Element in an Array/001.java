  class Solution {
        public int findKthLargest(int[] nums, int k) {
            if(nums==null || nums.length<1) return 0;
            if(k<1 || k>nums.length) return 0;
            int start = 0,end = nums.length-1;
            while (true)
            {
                int a=(start+end)/2,b=a;
                int i=start-1,j= end+1;
                int base = nums[a];
                while (true) {
                    if (i + 1 < a && nums[i + 1] == base) {
                        nums[i + 1] = nums[a - 1];
                        nums[a - 1] = base;
                        a--;
                    }
                    if (j - 1 > b && nums[j - 1] == base) {
                        nums[j-1] = nums[b+1];
                        nums[b+1] = base;
                        b++;
                    }
                    while (i + 1 <= end && nums[i + 1] < base) i++;
                    while (j - 1 >= start && nums[j - 1] > base) j--;
                    while ((a - 1 >= start) && nums[a - 1] == base) a--;
                    while ((b + 1 <= end) && nums[b + 1] == base) b++;
                    while(i + 1 <= end && nums[i + 1] > base && j - 1 >= start && nums[j - 1] < base)
                    {
                        int tmp = nums[i + 1];
                        nums[i+1] = nums[j-1];
                        nums[j-1] = tmp;
                        i++;
                        j--;
                    }
                    if(i+1==a && j-1>b)
                    {
                        nums[a]=nums[b+1];
                        nums[b+1] = base;
                        a++;
                        b++;
                        continue;
                    }
                    if(i+1<a && j-1==b)
                    {
                        nums[b]=nums[a-1];
                        nums[a-1] = base;
                        a--;
                        b--;
                        continue;
                    }

                    if (i + 1 == a && j - 1 == b)
                        break;

                }
                int size_larger  = end - j + 1;
                int size_equal   = b-a+1;
                int size_smaller = i- start + 1;
                if(k>size_larger && k <= (size_larger+size_equal) ) // in the equal group
                {
                    return nums[a];
                }
                if(k<=(size_larger)) // in the larger group
                {
                    start = j;
                }
                else
                {
                    end = i;
                    k = k- (size_larger+size_equal);
                }
            }

        }
    }