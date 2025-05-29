class Stack {
public:
    std::queue<int> q[2];
    // Push element x onto stack.
    void push(int x) {
        int i,j;
        i = (!q[0].empty())?0:1;
        j = 1 - i;
        q[j].push(x);
        while(!q[i].empty())
        {
            q[j].push(q[i].front());
            q[i].pop();
        }

    }

    // Removes the element on top of the stack.
    void pop() {
        if(!q[0].empty())
            return q[0].pop();
        else if(!q[1].empty())
            return q[1].pop();
        else
            return;
    }

    // Get the top element.
    int top() {
        if(!q[0].empty())
            return q[0].front();
        else if(!q[1].empty())
            return q[1].front();
        else
            return 0;
    }

    // Return whether the stack is empty.
    bool empty() {
        return q[0].empty() and q[1].empty();
    }
};