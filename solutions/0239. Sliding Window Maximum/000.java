class Solution {
     class Node
        {
            int value;
            int index;
            Node(int value,int index)
            {
                this.value=value;
                this.index=index;
            }
        }
        public int[] maxSlidingWindow(int[] nums, int k) {
           if(k<=0) return new int[0];
            int [] mw = new int[nums.length-k+1];
            List<Node> stack = new ArrayList<>();
            int bottom = 0,top = -1;
            int i = 0;
            while (i<nums.length)
            {

                while(top>=bottom && stack.get(top).value<=nums[i])
                {
                    stack.remove(top);
                    top--;
                }
                stack.add(new Node(nums[i],i));
                top++;
                i++;
                if(i>=k)
                {
                    mw[i-k]=stack.get(bottom).value;
                }
                if(i-k>=stack.get(bottom).index)
                {
                    bottom++;
                }
            }
            return mw;
        }
}