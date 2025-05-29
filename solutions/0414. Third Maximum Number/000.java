class Solution {
    int [] buffer = new int [4];
        int len = 0;
        void shiftBuffer(int start)
        {
            for(int i=len;i>=start+1;i--)
            {
                buffer[i]=buffer[i-1];
            }
        }
        void insBuffer(int x)
        {
            for(int i =0;i<len;i++)
            {
                if(buffer[i]==x) return;
                if(buffer[i]<x)
                {
                    shiftBuffer(i);
                    buffer[i] =x;
                    len++;
                    if(len>3) len=3;
                    return;
                }
            }
            buffer[len] =x;
            len++;
            if(len>3) len=3;
        }
        public int thirdMax(int[] nums) {
            for(int i=0;i < nums.length;i++)
            {
                insBuffer(nums[i]);
            }
            if(len==3)  return buffer[len-1];
            else return buffer[0];
        }
}