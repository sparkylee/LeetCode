class Solution {
    const bool dout0 = false;
    int getMaxIndex(vector<int>& height)
    {
        int m = 0; 
        int imax = -1;
        for(int i = 0; i < height.size(); i ++)
        {
            if(height[i] >= m)
            {
                m = height[i];
                imax = i;
            }
        }
        return imax;
    }
    void getStack(vector<int>& height, int m, vector<int> & stack, int dir)
    {
        stack.push_back(m);
        int endIndex = ((dir==1) ? (height.size()) : (-1));
        int i = m + dir;
        while(i != endIndex){
            int hi = height[i];
            while(!stack.empty())
            {
                int j = stack.back();
                int hj = height[j];
                //if( (hi < hj) || (hi==hj && (i-j==1 || j-i==1)))
                if( hi > hj)
                {
                    stack.pop_back();
                    if(dout0)
                    {
                     //   cout<<"popping "<<j<<endl;
                    }
                }
                else
                {
                    break;
                }
            }
            stack.push_back(i);
            i = i + dir;
        }
        return;
    }
    void printVector(vector<int> & stack)
    {
        for(int i = 0; i < stack.size(); i++)
        {
            cout<<stack[i]<<" ";
        }
        cout<<endl;
    }
    int calTrap(vector<int>& height,vector<int> & stack)
    {
        if(stack.empty())
            return 0;
        //int endIndex = ((dir==1) ? (height.size()) : (-1));
        int water = 0;
        
        for(int m = 0; m < stack.size() - 1 ; m++)
        {
            int i = stack[m] < stack[m+1] ? stack[m] : stack[m+1];
            int j = stack[m] > stack[m+1] ? stack[m] : stack[m+1];
            
            if(dout0)
            {
                cout<<"i="<<i<<" j="<<j<<endl;
            }
            int lowEdge = height[i] < height[j] ? height[i] : height[j];
            for(int k = i + 1; k < j; k ++)
            {
                int delta = lowEdge - height[k];
                water += delta;
                if(dout0)
                {
                    cout<<"k="<<k<<" "<<"delta="<<delta<<" water="<<water<<endl;
                }
            }
        }
        return water;
    }
    
public:
    int trap(vector<int>& height) {
        vector<int> lstack, rstack;
        int imax = getMaxIndex(height);
        if(imax == -1)
            return 0;
        getStack(height,imax,lstack,-1);
        getStack(height,imax,rstack, 1);
        if(dout0)    
        {
            cout<<"imax="<<imax<<endl;
            printVector(lstack);
            printVector(rstack);
        }
        
        int lwater = calTrap(height,lstack);
        int rwater = calTrap(height,rstack);
        if(dout0)    
        {
           cout<<"lwater="<<lwater<<endl;
           cout<<"rwater="<<rwater<<endl;
        }
        return lwater + rwater;
    }
};