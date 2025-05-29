class Solution {
   const bool dout0 = true;
   void merge(vector<int> & stack)
   {
       while(stack.size() >= 2 && stack.back() > 0 && stack[stack.size() -2] > 0)
       {
           stack[stack.size() - 2] =  stack.back() + stack[stack.size() -2];
           stack.pop_back();
       }
       return;
   }
   int maxValue(vector<int> & stack)
   {
       int m  = 0;
       for(int i = 0; i < stack.size() ; i ++)
       {
            if(stack[i] > m)
                m = stack[i];
       }
       return m;
   }
public:
    int longestValidParentheses(string s) {
        vector<int> stack;
        
        for(int i = 0; i < s.length(); i ++)
        {
           
            char c = s[i];
            if(c == ')' )  
            {
                if(  stack.empty() )
                {
                  stack.push_back(-1);
                  continue;
                }
                if(stack[stack.size() - 1] == 0)
                {
                   
                   stack[stack.size() - 1] = 2; 
                   merge(stack);
                   continue;
                }
                if(stack.size() >= 2 && stack[stack.size() - 1] > 0 && stack[stack.size() - 2] == 0)
                {
                   stack[stack.size() - 2] = 2; 
                   merge(stack);
                   continue;
                }
                stack.push_back(-1);
            }
            else//
                 stack.push_back(0);
           
        }
        return maxValue(stack);
      
    }
};